package core.ui.model

data class UIState<out T>(
        val data: T? = null,
        val loading: Boolean? = null,
        val message: String? = null,
        val specialMessage: String? = null,
        val forceLogin: String? = null,
)