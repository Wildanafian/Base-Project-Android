package core.ui.extension

import android.view.View
import core.ui.R

/**
 * Created by Wildan Nafian on 12/07/2023.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

fun View.setRvBackgroundGreen() {
    setBackgroundResource(R.color.green_fresh_rv)
}

fun View.setRvBackgroundWhite() {
    setBackgroundResource(R.color.white)
}

fun View.setBackgroundWhiteBorderGray() {
    setBackgroundResource(R.drawable.bg_white_border_gray_r10)
}

fun View.setBorderActiveGreen(status: Boolean) {
    setBackgroundResource(if (status) R.drawable.bg_white_border_primary_r10 else R.drawable.bg_white_border_gray_r10)
}

fun View.setCardRead(state: Boolean) {
    if (state) this.setRvBackgroundWhite() else this.setRvBackgroundGreen()
}

fun View.setBobaVisibility(state: Boolean) {
    if (state) this.setGone() else this.setVisile()
}
