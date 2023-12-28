package core.data.repository

import core.data.base.BaseRepository
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 24/10/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class SettingsRepositoryImpl @Inject constructor() : SettingsRepository, BaseRepository() {

    override fun setEverAskNotificationPermission(state: Boolean) {
        cacheManager.setEverAskNotificationPermission(state)
    }

    override fun checkIsEverAskNotificationPermission(): Boolean {
        return cacheManager.checkIsEverAskNotificationPermission()
    }

}