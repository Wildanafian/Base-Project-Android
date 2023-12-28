package core.common.util

object ProjectConstant {

    const val TAG = "@@"
    const val WARNING = 1
    const val INFO = 2
    const val ERROR = 3
    val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~\$^+=<>]).{8,}\$".toRegex()
    const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"
    const val DD_MMMM_YYYY = "dd MMMM yyyy"
    const val DD_MM_YYYY = "dd MM yyyy"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val HH_MM_SS = "HH:mm:ss"
    const val MM_SS = "mm:ss"
    const val CRED = "client_credentials"

}