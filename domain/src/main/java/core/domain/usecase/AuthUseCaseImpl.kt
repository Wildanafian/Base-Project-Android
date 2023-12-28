package core.domain.usecase

import core.data.repository.AuthRepository
import core.data.repository.SettingsRepository
import core.data.repository.TokenRepository
import core.model.base.ConsumeResultDomain
import core.model.data.request.LoginReq
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository,
    private val settingsRepository: SettingsRepository,
) : AuthUseCase {

    override fun getToken(): Flow<ConsumeResultDomain<String>> {
        return tokenRepository.getToken()
    }

    override fun doLogin(vaNumber: String, pin: String): Flow<ConsumeResultDomain<Boolean>> {
        return flow {
            tokenRepository.getToken().collect { resultToken ->
                when (resultToken) {
                    is ConsumeResultDomain.Success -> {
                        val request = LoginReq()
                        authRepository.doLogin(request).collect {
                            emit(ConsumeResultDomain.Success(data = true))
                        }
                    }

                    else                           -> with(resultToken as ConsumeResultDomain.Error) {
                        emit(ConsumeResultDomain.Error(code = code, message = message, data = false))
                    }
                }
            }
        }
    }

    override fun checkIsLogin(): Boolean {
        return authRepository.checkIsLogin()
    }

    override fun doClearData() {
        authRepository.doClearData()
    }

    override fun setEverAskNotificationPermission(state: Boolean) {
        settingsRepository.setEverAskNotificationPermission(state)
    }

    override fun checkIsEverAskNotificationPermission(): Boolean {
        return settingsRepository.checkIsEverAskNotificationPermission()
    }
}