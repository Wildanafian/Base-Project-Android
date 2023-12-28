package core.domain.usecase

import core.data.repository.AuthRepository
import core.model.base.ConsumeResultDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 05/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class HomeUseCaseImpl @Inject constructor(
        private val authRepository: AuthRepository,
) : HomeUseCase {

    override fun getData(): Flow<ConsumeResultDomain<String>> {
        return flow { ConsumeResultDomain.Success(data = "") }
    }

    override fun doLogout() {
        authRepository.doLogout()
    }
}