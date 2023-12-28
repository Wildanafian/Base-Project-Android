package core.ui.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding
import core.ui.R
import core.ui.base.common.BaseCommonFunction
import core.ui.extension.Inflate
import core.ui.extension.gooTo

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@SuppressLint("InflateParams")
abstract class BaseFragment<out VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment(), BaseCommonFunction {

    private var _binding: VB? = null
    protected val bind get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate(inflater)
        initView()
        initListener()
        initObserver()
        return bind.root
    }

    override fun onDestroyView() {
        showLoadingDialog(false)
        _binding = null
        super.onDestroyView()
    }

    override fun initView() = Unit

    override fun initListener() = Unit

    override fun initObserver() = Unit

    protected fun displayTopInfoMessage(message: String) {
        val intent = Intent("error_msg")
        intent.putExtra("message", message)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    protected fun displayMessage(
        title: String,
        button: String? = "OK",
        icon: Int? = R.drawable.ic_failed_red,
        ok: (() -> Unit)? = null,
    ) = (activity as BaseActivity<*>).displayMessage(title = title, button = button, icon = icon, ok = ok)

    protected fun showLoadingDialog(status: Boolean) = (activity as BaseActivity<*>).showLoadingDialog(status)

    fun String.forceLogout(directions: NavDirections) = displayMessage(this, "Login", R.drawable.ic_failed_red) { gooTo(directions) }
}