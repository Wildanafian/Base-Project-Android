package feature.profile.ui.dialog

import core.ui.base.BaseDialogFragment
import feature.profile.databinding.DialogLogoutBinding

/**
 * Created by Wildan Nafian on 23/09/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
class LogoutConfirmDialog(private val keluar: (() -> Unit)? = null) : BaseDialogFragment<DialogLogoutBinding>(DialogLogoutBinding::inflate) {

    override fun initListener() {
        bind.btnOut.listener {
            keluar?.invoke()
        }

        bind.btnClose.listener {}
    }
}