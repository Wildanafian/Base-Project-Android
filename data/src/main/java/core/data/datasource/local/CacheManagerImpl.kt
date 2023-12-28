package core.data.datasource.local

import core.common.util.fromJson
import core.common.util.toJson
import core.datastore.constant.SharedPrefKey
import core.datastore.constant.SharedPrefKey.everAskNotifPermission
import core.datastore.helper.SharedPreferenceHelper
import core.model.data.response.LoginRes
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 25/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class CacheManagerImpl @Inject constructor(private val sharedPreferences: SharedPreferenceHelper) : CacheManager {

    override fun cacheLoginData(data: LoginRes) {
        sharedPreferences.cacheTo(SharedPrefKey.personalData, data.toJson())
    }

    override fun getLoginData(): LoginRes {
        return sharedPreferences.getCache(SharedPrefKey.personalData).fromJson<LoginRes>() ?: LoginRes()
    }

    override fun setEverAskNotificationPermission(state: Boolean) {
        sharedPreferences.cacheTo(everAskNotifPermission, state)
    }

    override fun checkIsEverAskNotificationPermission(): Boolean {
        return sharedPreferences.getCacheBoolean(everAskNotifPermission)
    }
}