package feature.auth.ui.register

import androidx.fragment.app.viewModels
import core.ui.base.BaseFragment
import core.ui.extension.goBack
import core.ui.extension.listener
import core.ui.extension.setBorderActiveGreen
import core.ui.extension.setToolbarBackNavigationAction
import dagger.hilt.android.AndroidEntryPoint
import feature.auth.databinding.FragmentRegisterBinding

/**
 * Created by Wildan Nafian on 13/12/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun initView() = with(bind) {
        toolbar.setToolbarBackNavigationAction(this@RegisterFragment)
    }

    override fun initListener() = with(bind) {
        etName.setOnFocusChangeListener { _, hasFocus ->
            containerName.setBorderActiveGreen(hasFocus)
        }

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            containerEmail.setBorderActiveGreen(hasFocus)
        }

        etPassword.setOnFocusChangeListener { _, hasFocus ->
            containerPassword.setBorderActiveGreen(hasFocus)
        }

        etRetypePassword.setOnFocusChangeListener { _, hasFocus ->
            containerRetypePassword.setBorderActiveGreen(hasFocus)
        }

        btnSubmit.listener {
            goBack()
        }
    }
}
