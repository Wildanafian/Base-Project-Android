package core.datastore.helper

/**
 * Created by Wildan Nafian on 25/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface SharedPreferenceHelper {

    fun cacheTo(key: String, data: String?)
    fun cacheTo(key: String, data: Int?)
    fun cacheTo(key: String, data: Boolean)

    fun getCache(key: String): String
    fun getCacheInt(key: String): Int
    fun getCacheBoolean(key: String): Boolean

    fun clearAllCacheData()
    fun clearCacheByKey(key: String)
}