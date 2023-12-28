package feature.entryPoint.ui

import androidx.fragment.app.viewModels
import core.ui.base.BaseFragment
import core.ui.extension.gooTo
import dagger.hilt.android.AndroidEntryPoint
import feature.entryPoint.databinding.FragmentStartBinding

/**
 * Created by Wildan Nafian on 13/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@AndroidEntryPoint
class StartingFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    private val viewModel: StartingViewModel by viewModels()

    override fun initView() {
        if (viewModel.checkIsLogin()) gooTo(StartingFragmentDirections.toHome())
        else {
            viewModel.clearCacheData()
            gooTo(StartingFragmentDirections.toLogin())
        }
    }

}