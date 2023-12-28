package core.network.helper

import core.model.base.RemoteResult
import retrofit2.Response

interface RemoteHelper {
    suspend fun <T> getRemoteResult(apiCall: Response<T>): RemoteResult<T>
}