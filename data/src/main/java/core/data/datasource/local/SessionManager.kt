package core.data.datasource.local

/**
 * Created by Wildan Nafian on 10/11/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

interface SessionManager {

    fun clearAllCache()
    fun clearUserCache()

    fun setLoginStatus(data: Boolean)
    fun checkLoginStatus(): Boolean

    fun setSessionTimeExpired(time: String)
    fun checkIsOnSession(): Boolean

    fun cacheXKey(xKey: String)
    fun getXKey(): String
    fun getRefreshKey(): String

}