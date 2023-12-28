package core.data.datasource.remote

import core.common.util.ProjectConstant.CRED
import core.data.base.BaseRemote
import core.model.base.ConsumeResultRemote
import core.model.base.RemoteResult
import core.model.data.request.TokenReq
import core.model.data.response.TokenRes
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 31/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class TokenRemoteImpl @Inject constructor() : TokenRemote, BaseRemote() {

    override suspend fun getToken(): ConsumeResultRemote<TokenRes> {
        return when (val remoteResult = getRemoteResult(api.getToken(TokenReq(CRED)))) {
            is RemoteResult.Success                                  -> ConsumeResultRemote.Success(data = remoteResult.data.data ?: TokenRes())
            is RemoteResult.Error -> ConsumeResultRemote.Error(code = remoteResult.data.code, message = remoteResult.data.message)
        }
    }

    fun doRefreshToken(): ConsumeResultRemote<String> {
        return ConsumeResultRemote.Error(code = null, message = null, messageId = null)
    }

}
