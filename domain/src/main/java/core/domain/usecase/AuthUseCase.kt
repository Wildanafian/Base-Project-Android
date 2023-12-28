package core.domain.usecase

import core.model.base.ConsumeResultDomain
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun getToken(): Flow<ConsumeResultDomain<String>>
    fun doLogin(vaNumber: String, pin: String): Flow<ConsumeResultDomain<Boolean>>
    fun checkIsLogin(): Boolean
    fun setEverAskNotificationPermission(state: Boolean)
    fun checkIsEverAskNotificationPermission(): Boolean
    fun doClearData()

}