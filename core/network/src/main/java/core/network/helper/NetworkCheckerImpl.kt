package core.network.helper

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 24/08/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@Suppress("DEPRECATION")
class NetworkCheckerImpl @Inject constructor(private val connectivityManager: ConnectivityManager) : NetworkChecker {

    override fun isConnected(): Boolean {
        if (isSdkAboveOrAtLeastAndroidM()) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)     -> return true
                }
            }
        } else {
            var result = false
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI     -> true
                        ConnectivityManager.TYPE_MOBILE   -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else                              -> false
                    }

                }
            }
            return result
        }
        return false
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
    private fun isSdkAboveOrAtLeastAndroidM(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}