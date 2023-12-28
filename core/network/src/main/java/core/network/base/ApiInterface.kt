package core.network.base

import core.model.base.BaseResponse
import core.model.data.request.LoginReq
import core.model.data.request.TokenReq
import core.model.data.response.LoginRes
import core.model.data.response.TokenRes
import core.network.constant.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST(EndPoint.GET_TOKEN)
    suspend fun getToken(@Body body: TokenReq): Response<BaseResponse<TokenRes>>

    @POST(EndPoint.LOGIN)
    suspend fun doLogin(@Body body: LoginReq): Response<BaseResponse<LoginRes>>

}