package core.domain.usecase

interface StartingUseCase {

    fun checkIsLogin(): Boolean
    fun checkSession(): Boolean
    fun doClearData()
}