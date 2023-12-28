package core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import core.ui.R
import core.ui.extension.Inflate

/**
 * Created by Wildan Nafian on 03/08/2023.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
abstract class BaseBottomSheet<out VB : ViewBinding>(private val inflate: Inflate<VB>) : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val bind get() = _binding!!

    var listener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = inflate(inflater)
        initView()
        initListeners()
        return bind.root
    }

    protected open fun initView() = Unit

    protected open fun initListeners() = Unit

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}