package feature.entryPoint.ui

import core.domain.usecase.StartingUseCase
import core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 23/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@HiltViewModel
class StartingViewModel @Inject constructor(private val startingUseCase: StartingUseCase) : BaseViewModel() {

    fun clearCacheData() = startingUseCase.doClearData()

    fun checkIsLogin() = startingUseCase.checkIsLogin() && startingUseCase.checkSession()

}