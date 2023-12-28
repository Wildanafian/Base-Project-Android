package core.ui.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import core.ui.R
import core.ui.base.common.BaseCommonFunction
import core.ui.extension.Inflate

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@SuppressLint("InflateParams")
abstract class BaseActivity<out VB : ViewBinding>(private val inflate: Inflate<VB>) : AppCompatActivity(), BaseCommonFunction {

    private var _binding: VB? = null
    protected val bind get() = _binding!!

    private var messageDialog: InfoDialog? = null

    private val loadingDialog by lazy {
        Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view = LayoutInflater.from(this@BaseActivity).inflate(R.layout.custom_loading, null)
            setContentView(view)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window!!.attributes = WindowManager.LayoutParams().apply {
                copyFrom(window!!.attributes)
                width = WindowManager.LayoutParams.WRAP_CONTENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER
            }
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        initView()
        initListener()
        initObserver()
    }

    override fun onPause() {
        super.onPause()
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        showLoadingDialog(false)
        messageDialog?.dismiss()
        _binding = null
        loadingDialog.dismiss()
    }

    override fun initView() = Unit

    override fun initListener() = Unit

    override fun initObserver() = Unit

    fun displayInfoMessage(message: String) {
        displayMessage(message, "OK", R.drawable.ic_failed_red)
    }

    fun displayMessage(
        title: String,
        button: String? = "OK",
        icon: Int? = R.drawable.ic_failed_red,
        ok: (() -> Unit)? = null,
    ) {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
        initMessageDialog {
            messageDialog?.addMessage(title)
            messageDialog?.buttonText?.value = button
            messageDialog?.icon?.value = icon
            messageDialog?.listener = { ok?.invoke() }
        }
    }

    private fun initMessageDialog(callback: () -> Unit) {
        if (messageDialog == null) {
            messageDialog = InfoDialog()
            callback()
            messageDialog?.show(supportFragmentManager, messageDialog?.tags)
        } else {
            callback()
            messageDialog?.dialog?.show()
        }
    }

    fun showLoadingDialog(status: Boolean) {
        if (status) loadingDialog.show() else loadingDialog.hide()
    }
}