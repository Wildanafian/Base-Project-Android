package core.datastore.helper

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 25/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class SharedPreferenceHelperImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
        SharedPreferenceHelper {

    override fun cacheTo(key: String, data: String?) {
        sharedPreferences.edit().putString(key, data ?: "").apply()
    }

    override fun cacheTo(key: String, data: Int?) {
        sharedPreferences.edit().putInt(key, data ?: 0).apply()
    }

    override fun cacheTo(key: String, data: Boolean) {
        sharedPreferences.edit().putBoolean(key, data).apply()
    }

    override fun getCache(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun getCacheInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun getCacheBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun clearAllCacheData() {
        sharedPreferences.edit().clear().apply()
    }

    override fun clearCacheByKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}