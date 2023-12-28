package feature.profile.ui

import androidx.fragment.app.viewModels
import core.ui.base.BaseFragment
import core.ui.extension.gooTo
import core.ui.extension.listener
import dagger.hilt.android.AndroidEntryPoint
import feature.profile.databinding.FragmentProfileBinding

/**
 * Created by Wildan Nafian on 23/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val vm: ProfileViewModel by viewModels()

    override fun initListener() {
        bind.btnEdit.listener {

        }

        bind.btnLogout.listener {
            vm.doLogout()
            gooTo(ProfileFragmentDirections.toLogin())
        }
    }

}