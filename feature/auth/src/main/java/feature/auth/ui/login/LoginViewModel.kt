package feature.auth.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import core.domain.usecase.AuthUseCase
import core.model.base.ConsumeResultDomain
import core.ui.base.BaseViewModel
import core.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 23/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthUseCase) : BaseViewModel() {

    private var _login = MutableLiveData<UIState<Boolean>>()
    val login: LiveData<UIState<Boolean>> = _login

    fun doLogin(username: String, pin: String) = mainScope.launch {
        useCase.doLogin(username, pin)
            .onStart { _login.value = UIState(loading = true) }
            .collect { data ->
                when (data) {
                    is ConsumeResultDomain.Success                                  -> _login.value = UIState(data = data.data, loading = false)
                    is ConsumeResultDomain.Error -> _login.value = UIState(message = data.message, loading = false)
                    else                                                            -> _login.value = UIState(message = "Unknown", loading = false)
                }
            }
    }

}