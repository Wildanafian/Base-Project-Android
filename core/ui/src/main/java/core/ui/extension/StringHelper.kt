package core.ui.extension

import androidx.fragment.app.Fragment
import core.ui.R

/**
 * Created by Wildan Nafian on 10/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

fun Fragment.getNoConnection(): String = context?.getString(R.string.no_internet).orEmpty()

fun Fragment.getSuccess(): String = context?.getString(R.string.success).orEmpty()

fun Fragment.getSukses(): String = "Sukses"

fun Fragment.getRegisterHere(): String = context?.getString(R.string.register_here).orEmpty()

fun Fragment.getHeader(): String = context?.getString(R.string.header).orEmpty()