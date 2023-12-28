package core.model.base

sealed class ConsumeResultDomain<out R> {
    data class Success<out T>(val data: T) : ConsumeResultDomain<T>()
    data class Error<out T>(val code: String? = "", val message: String, val data: T? = null) : ConsumeResultDomain<T>()
    data class ErrorAuth(val message: String) : ConsumeResultDomain<Nothing>()
}