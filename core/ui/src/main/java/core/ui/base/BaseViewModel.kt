package core.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import core.ui.extension.Event
import core.ui.model.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

/**
 * Created by Wildan Nafian on 05/08/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
open class BaseViewModel : ViewModel() {

    private val job = SupervisorJob()
    protected val mainScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        mainScope.coroutineContext.cancelChildren()
    }

    @JvmName("update")
    fun <T> MutableLiveData<UIState<T>>.update(data: T? = null, loading: Boolean? = false, message: String? = null, specialMessage: String? = null, forceLogin: String? = null) {
        value = UIState(data, loading, message, specialMessage, forceLogin)
    }

    @JvmName("updateEvent")
    fun <T> MutableLiveData<Event<UIState<T>>>.update(data: T? = null, loading: Boolean? = false, message: String? = null, specialMessage: String? = null, forceLogin: String? = null) {
        value = Event(UIState(data, loading, message, specialMessage, forceLogin))
    }

    @JvmName("update")
    fun <T> MutableLiveData<UIState<T>>.setDefaultError() {
        value = UIState(message = "Unknown")
    }

    @JvmName("updateEvent")
    fun <T> MutableLiveData<Event<UIState<T>>>.setDefaultError() {
        value = Event(UIState(message = "Unknown"))
    }
}