package core.domain.usecase

import core.data.repository.AuthRepository
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 07/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class ProfileUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : ProfileUseCase {

    override fun doLogout() {
        authRepository.doLogout()
    }

    override fun getProfileData() = ""

}