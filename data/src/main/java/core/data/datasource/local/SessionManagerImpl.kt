package core.data.datasource.local

import core.datastore.constant.SharedPrefKey
import core.datastore.constant.SharedPrefKey.sessionEnded
import core.datastore.helper.SharedPreferenceHelper
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 25/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class SessionManagerImpl @Inject constructor(private val sharedPreferences: SharedPreferenceHelper) : SessionManager {

    override fun clearAllCache() {
        sharedPreferences.clearAllCacheData()
    }

    override fun clearUserCache() {
        sharedPreferences.clearCacheByKey(SharedPrefKey.personalData)
        sharedPreferences.clearCacheByKey(SharedPrefKey.xKey)
        sharedPreferences.cacheTo(sessionEnded, "0")
        sharedPreferences.cacheTo(SharedPrefKey.IS_LOGIN, false)
    }

    override fun cacheXKey(xKey: String) {
        sharedPreferences.cacheTo(SharedPrefKey.xKey, xKey)
    }

    override fun setLoginStatus(data: Boolean) {
        sharedPreferences.cacheTo(SharedPrefKey.IS_LOGIN, data)
    }

    override fun getRefreshKey(): String {
        return sharedPreferences.getCache(SharedPrefKey.refreshXKey)
    }

    override fun setSessionTimeExpired(time: String) {
        val expiredInMilis = (time.toLong()) * 1000
        val expiredTime = System.currentTimeMillis() + expiredInMilis
        sharedPreferences.cacheTo(sessionEnded, expiredTime.toString())
    }

    override fun checkLoginStatus(): Boolean {
        return sharedPreferences.getCacheBoolean(SharedPrefKey.IS_LOGIN)
    }

    override fun getXKey(): String {
        return sharedPreferences.getCache(SharedPrefKey.xKey)
    }

    override fun checkIsOnSession(): Boolean {
        val timeNow = System.currentTimeMillis()
        val timeExpired = sharedPreferences.getCache(sessionEnded).ifEmpty { "0" }.toLong()
        val result = timeExpired - timeNow
        return result > 0
    }

}