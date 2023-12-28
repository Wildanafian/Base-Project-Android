package core.data.repository

import core.model.base.ConsumeResultDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by Wildan Nafian on 12/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

fun interface TokenRepository {
    fun getToken(): Flow<ConsumeResultDomain<String>>
}