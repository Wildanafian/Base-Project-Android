package core.datastore.di

import android.content.Context
import android.content.SharedPreferences
import android.security.KeyPairGeneratorSpec
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import core.datastore.helper.isSdkAboveOrAtLeastAndroidM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Singleton
import javax.security.auth.x500.X500Principal

/**
 * Created by Wildan Nafian on 12/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@Suppress("DEPRECATION")
@Module
@InstallIn(SingletonComponent::class)
object ProvideEncryptedSharedPreference {

    @Singleton
    @Provides
    @Synchronized
    fun provideInitSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            "YourName",
            createMasterKey(context),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun createMasterKey(context: Context): String {
        return if (isSdkAboveOrAtLeastAndroidM()) {
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        } else {
            val alias = "other_mk"
            val start: Calendar = GregorianCalendar()
            val end: Calendar = GregorianCalendar()
            end.add(Calendar.YEAR, 30)

            val spec =
                KeyPairGeneratorSpec.Builder(context)
                    .setAlias(alias)
                    .setSubject(X500Principal("CN=$alias"))
                    .setSerialNumber(
                        BigInteger.valueOf(
                            Math.abs(alias.hashCode()).toLong()
                        )
                    )
                    .setStartDate(start.time).setEndDate(end.time)
                    .build()

            val kpGenerator: KeyPairGenerator = KeyPairGenerator.getInstance(
                "RSA",
                "AndroidKeyStore"
            )
            kpGenerator.initialize(spec)
            val kp: KeyPair = kpGenerator.generateKeyPair()
            kp.public.toString()
        }
    }
}