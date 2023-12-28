package feature.profile.ui

import core.domain.usecase.ProfileUseCase
import core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 23/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: ProfileUseCase) : BaseViewModel() {

    fun doLogout() = useCase.doLogout()

}