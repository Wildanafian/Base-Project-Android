package core.data.repository

import core.common.util.ifEmptyOrNull
import core.data.base.BaseRepository
import core.data.datasource.local.SessionManager
import core.data.datasource.remote.TokenRemote
import core.model.base.ConsumeResultDomain
import core.model.base.ConsumeResultRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 12/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

class TokenRepositoryImpl @Inject constructor(val remote: TokenRemote, private val sessionManager: SessionManager) : BaseRepository(), TokenRepository {

    override fun getToken(): Flow<ConsumeResultDomain<String>> {
        return flow {
            when (val result = remote.getToken()) {
                is ConsumeResultRemote.Success -> {
                    sessionManager.cacheXKey(result.data.accessToken.orEmpty())
                    sessionManager.setSessionTimeExpired(result.data.expiresIn.ifEmptyOrNull { "3600" })
                    emit(ConsumeResultDomain.Success(""))
                }

                is ConsumeResultRemote.Error     -> emit(ConsumeResultDomain.Error(code = result.code, message = result.messageId.orEmpty()))
                is ConsumeResultRemote.ErrorAuth -> Unit
            }
        }.catch {
            emit(ConsumeResultDomain.Error(message = it.localizedMessage.orEmpty()))
        }.flowOn(ioDispatcher)
    }

}