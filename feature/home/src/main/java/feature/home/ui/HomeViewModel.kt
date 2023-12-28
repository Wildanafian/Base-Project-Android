package feature.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import core.domain.usecase.HomeUseCase
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
class HomeViewModel @Inject constructor(private val useCase: HomeUseCase) : BaseViewModel() {

    private var _home = MutableLiveData<UIState<String>>()
    val home: LiveData<UIState<String>> = _home

    fun getData() = mainScope.launch {
        useCase.getData()
            .onStart { _home.value = UIState(loading = true) }
            .collect {
                when (it) {
                    is ConsumeResultDomain.Success                                  -> _home.value = UIState(data = it.data, loading = false)
                    is ConsumeResultDomain.Error -> _home.value = UIState(message = it.message, loading = false)

                    is ConsumeResultDomain.ErrorAuth -> {
                        useCase.doLogout()
                        _home.value = UIState(specialMessage = it.message, loading = false)
                    }
                }
            }
    }

}
