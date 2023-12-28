package feature.auth.ui.login

import android.annotation.SuppressLint
import android.text.Html
import androidx.fragment.app.viewModels
import core.ui.base.BaseFragment
import core.ui.extension.getHeader
import core.ui.extension.getRegisterHere
import core.ui.extension.getTexts
import core.ui.extension.gooTo
import core.ui.extension.listener
import core.ui.extension.observe
import core.ui.extension.setBorderActiveGreen
import dagger.hilt.android.AndroidEntryPoint
import feature.auth.databinding.FragmentLoginBinding

/**
 * Created by Wildan Nafian on 19/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val vm: LoginViewModel by viewModels()

    override fun initView() {
        bind.tvHeader.text = Html.fromHtml(getHeader(),1)
        bind.tvRegisterHere.text = Html.fromHtml(getRegisterHere(),1)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        bind.btnEnter.listener {
            vm.doLogin(bind.etEmail.getTexts(), bind.etPassword.getTexts())
        }

        bind.etEmail.setOnFocusChangeListener { _, hasFocus ->
            bind.containerEmail.setBorderActiveGreen(hasFocus)
        }

        bind.etPassword.setOnFocusChangeListener { _, hasFocus ->
            bind.containerPassword.setBorderActiveGreen(hasFocus)
        }

        bind.tvRegisterHere.listener {
            gooTo(LoginFragmentDirections.toRegister())
        }
    }

    override fun initObserver() {
        observe(vm.login) { data ->
            data?.data?.let { gooTo(LoginFragmentDirections.toHome()) }
            data?.message?.let { displayTopInfoMessage(it) }
            data?.loading?.let { showLoadingDialog(it) }
        }
    }

}