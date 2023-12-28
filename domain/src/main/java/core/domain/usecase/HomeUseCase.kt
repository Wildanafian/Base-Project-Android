package core.domain.usecase

import core.model.base.ConsumeResultDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by Wildan Nafian on 05/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface HomeUseCase {
    fun getData(): Flow<ConsumeResultDomain<String>>
    fun doLogout()
}