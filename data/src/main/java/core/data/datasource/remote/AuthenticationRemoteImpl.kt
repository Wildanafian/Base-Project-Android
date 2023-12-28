package core.data.datasource.remote

import core.data.base.BaseRemote
import core.model.base.BaseResponse
import core.model.base.ConsumeResultRemote
import core.model.base.RemoteResult
import core.model.data.request.LoginReq
import core.model.data.response.LoginRes
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 03/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class AuthenticationRemoteImpl @Inject constructor() : AuthenticationRemote, BaseRemote() {

    override suspend fun doLogin(body: LoginReq): ConsumeResultRemote<LoginRes> {
        return when (val remoteResult = getRemoteResult(api.doLogin(body))) {
            is RemoteResult.Success                                  -> ConsumeResultRemote.Success(data = remoteResult.data.data ?: LoginRes())
            is RemoteResult.Error -> ConsumeResultRemote.Error(code = remoteResult.data.code, message = remoteResult.data.message)
        }
    }

    override suspend fun doLogout(username: String): ConsumeResultRemote<BaseResponse<String>> {
        return ConsumeResultRemote.Error(code = null, message = null, messageId = null)
    }
}
