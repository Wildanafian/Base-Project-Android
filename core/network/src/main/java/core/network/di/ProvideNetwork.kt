package core.network.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import core.datastore.constant.SharedPrefKey.xKey
import core.datastore.helper.SharedPreferenceHelper
import core.network.BuildConfig
import core.network.R
import core.network.base.ApiInterface
import core.network.helper.connection.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by Wildan Nafian on 12/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object ProvideNetwork {

    private fun getBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideApi(pref: SharedPreferenceHelper, @NetworkConnectionInterceptor noNetworkInterceptor: Interceptor): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(setupClient(pref, noNetworkInterceptor))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ApiInterface::class.java)
    }

    private fun setupClient(pref: SharedPreferenceHelper, noNetworkInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(50L, TimeUnit.SECONDS)
            .connectTimeout(50L, TimeUnit.SECONDS)
            .writeTimeout(50L, TimeUnit.SECONDS)
            .addNetworkInterceptor {
                val headers = it.request().addHeaders(pref.getCache(xKey))
                return@addNetworkInterceptor it.proceed(headers.build())
            }
            .addInterceptor(noNetworkInterceptor)
            .also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }
            .build()
    }

    private fun Request.addHeaders(xKey: String): Request.Builder {

        return newBuilder().apply {
            header("Content-Type", "application/json")
            header("Authorization", "Bearer $xKey")
        }
    }

    private fun RequestBody?.getRequestBody(): String {
        return try {
            val buffer = Buffer()
            if (this != null) writeTo(buffer) else return ""
            buffer.readUtf8()
        } catch (e: Exception) {
            ""
        }
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Singleton
    @Provides
    @NetworkConnectionInterceptor
    fun provideInterceptor(@ApplicationContext context: Context, connectivityManager: ConnectivityManager): Interceptor = NetworkConnectionInterceptor(context.getString(R.string.no_internet), connectivityManager)

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class NetworkConnectionInterceptor

}