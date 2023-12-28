package core.ui.extension

import android.content.Context
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import core.ui.R

/**
 * Created by Wildan Nafian on 12/07/2023.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

fun Fragment.getGreen() = ContextCompat.getColor(this.requireContext(), R.color.primary)

fun Context.getGreen() = ContextCompat.getColor(this, R.color.primary)

fun getGreenLogin() = R.color.green_splash

fun getTransparent() = android.R.color.transparent

fun getWhite() = R.color.white

fun getGrayEdittext() = R.color.gray_edittext

fun CardView.setCardBackgroundActive(state: Boolean) {
    setCardBackgroundColor(ContextCompat.getColor(this.context, if (state) R.color.primary else R.color.white))
}

fun TextView.setTextColorWhiteBlack(state: Boolean) {
    setTextColor(ContextCompat.getColor(this.context, if (state) R.color.white else R.color.black))
}