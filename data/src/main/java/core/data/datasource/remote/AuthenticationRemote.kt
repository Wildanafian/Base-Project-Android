package core.data.datasource.remote

import core.model.base.BaseResponse
import core.model.base.ConsumeResultRemote
import core.model.data.request.LoginReq
import core.model.data.response.LoginRes

/**
 * Created by Wildan Nafian on 03/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface AuthenticationRemote {
    suspend fun doLogin(body: LoginReq): ConsumeResultRemote<LoginRes>
    suspend fun doLogout(username: String): ConsumeResultRemote<BaseResponse<String>>
}