package core.common.util

import core.common.BuildConfig
import timber.log.Timber

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Log
 * yourString.printLog(optional tag)
 * yourString.printLog("@@")
 */
fun String?.printLog(tag: String = ProjectConstant.TAG) {
    if (BuildConfig.DEBUG) Timber.tag(tag).d(this ?: "null")
}

fun Exception.printLog(tag: String = ProjectConstant.TAG) {
    if (BuildConfig.DEBUG) Timber.tag(tag).i(this.localizedMessage)
}

fun Throwable.printLog(tag: String = ProjectConstant.TAG) {
    if (BuildConfig.DEBUG) Timber.tag(tag).i(this.localizedMessage)
}