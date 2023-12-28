package feature.home.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import core.ui.base.BaseFragment
import core.ui.extension.handleTwiceBackPress
import core.ui.extension.listener
import core.ui.extension.observe
import dagger.hilt.android.AndroidEntryPoint
import feature.home.databinding.FragmentHomeBinding

/**
 * Created by Wildan Nafian on 21/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val vm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleTwiceBackPress()
    }

    override fun initView() {

    }

    override fun initListener() {
        bind.swipe.listener {
            vm.getData()
        }
    }

    override fun initObserver() {
        observe(vm.home) { data ->
            data?.data?.let {}
            data?.message?.let { displayTopInfoMessage(it) }
            data?.specialMessage?.forceLogout(HomeFragmentDirections.toLogin())
            data?.loading?.let { showLoadingDialog(it) }
        }
    }

}