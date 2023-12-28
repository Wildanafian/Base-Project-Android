package core.data.repository

import core.data.base.BaseRepository
import core.data.datasource.local.SessionManager
import core.data.datasource.remote.AuthenticationRemote
import core.model.base.ConsumeResultDomain
import core.model.base.ConsumeResultRemote
import core.model.data.request.LoginReq
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

class AuthRepositoryImpl @Inject constructor(val remote: AuthenticationRemote, private val sessionManager: SessionManager) : BaseRepository(), AuthRepository {

    override fun doLogin(body: LoginReq): Flow<ConsumeResultDomain<Boolean>> {
        return flow {
            when (val result = remote.doLogin(body)) {
                is ConsumeResultRemote.Success -> {
                    cacheManager.cacheLoginData(result.data)
                    emit(ConsumeResultDomain.Success(true))
                }

                is ConsumeResultRemote.Error -> emit(ConsumeResultDomain.Error(code = result.code, message = result.message.orEmpty()))

                is ConsumeResultRemote.ErrorAuth -> emit(ConsumeResultDomain.ErrorAuth(result.message.orEmpty()))
            }
        }.catch {
            emit(ConsumeResultDomain.Error(message = it.localizedMessage.orEmpty()))
        }.flowOn(ioDispatcher)
    }

    override fun doLogout() {
        doClearData()
    }

    override fun checkIsLogin(): Boolean {
        return sessionManager.checkLoginStatus()
    }

    override fun doClearData() {
        sessionManager.clearUserCache()
    }

    override fun checkIsInSession(): Boolean {
        return sessionManager.checkIsOnSession()
    }
}