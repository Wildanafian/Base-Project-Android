package core.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import core.ui.base.common.BaseCommonFunction
import core.ui.extension.Inflate

/**
 * Created by Wildan Nafian on 25/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */


abstract class BaseDialogFragment<out VB : ViewBinding>(private val inflate: Inflate<VB>) : DialogFragment(), BaseCommonFunction {

    private var _binding: VB? = null
    protected val bind get() = _binding!!
    val tags = "tags1"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate(inflater)
        initView()
        initListener()
        initObserver()
        return bind.root
    }

    override fun initView() = Unit
    override fun initListener() = Unit
    override fun initObserver() = Unit

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false
    }

    fun View.listener(callback: () -> Unit) {
        this.setOnClickListener {
            callback()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}