package core.domain.usecase

import core.data.repository.AuthRepository
import javax.inject.Inject

class StartingUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : StartingUseCase {

    override fun checkIsLogin(): Boolean {
        return authRepository.checkIsLogin()
    }

    override fun checkSession(): Boolean {
        return authRepository.checkIsInSession()
    }

    override fun doClearData() {
        authRepository.doClearData()
    }

}