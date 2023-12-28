package core.network.helper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import core.model.base.BaseResponse
import core.model.base.RemoteResult
import core.network.helper.connection.NoConnectivityException
import retrofit2.Response

class RemoteHelperImpl : RemoteHelper {

    override suspend fun <T> getRemoteResult(apiCall: Response<T>): RemoteResult<T> {
        return try {
            if (apiCall.isSuccessful) {
                val body = apiCall.body()
                if (body != null) RemoteResult.Success(body) else RemoteResult.Error(apiCall.code().toString(), BaseResponse(messageId = "body is null", messageEn = "body is null"))
            } else {
                val rawData = apiCall.errorBody()?.string()
                val data = Gson().fromJson<BaseResponse<String>>(rawData, object : TypeToken<BaseResponse<String>>() {}.type)
                RemoteResult.Error(apiCall.code().toString(), data)
            }
        } catch (e: Exception) {
            if (e is NoConnectivityException) RemoteResult.Error("999", BaseResponse(messageId = e.localizedMessage, messageEn = e.localizedMessage))
            else RemoteResult.Error(apiCall.code().toString(), BaseResponse(messageId = e.localizedMessage, messageEn = e.localizedMessage))
        }
    }
}