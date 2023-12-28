package core.model.base

sealed class RemoteResult<out R> {
    data class Success<out T>(val data: T) : RemoteResult<T>()
    data class Error(val code: String, val data: BaseResponse<String>, val cause: Exception? = null) : RemoteResult<Nothing>()
}