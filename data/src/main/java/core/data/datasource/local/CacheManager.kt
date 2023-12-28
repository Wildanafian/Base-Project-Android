package core.data.datasource.local

import core.model.data.response.LoginRes

/**
 * Created by Wildan Nafian on 10/11/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface CacheManager {

    fun cacheLoginData(data: LoginRes)
    fun getLoginData(): LoginRes

    fun setEverAskNotificationPermission(state: Boolean)
    fun checkIsEverAskNotificationPermission(): Boolean
}