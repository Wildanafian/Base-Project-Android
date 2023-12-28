package core.data.repository

/**
 * Created by Wildan Nafian on 24/10/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface SettingsRepository {

    fun setEverAskNotificationPermission(state: Boolean)
    fun checkIsEverAskNotificationPermission(): Boolean

}
