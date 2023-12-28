package core.common.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import kotlin.math.ceil

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

fun String?.roundUpDown() = if (this?.contains(".") == true) {
    if (this.takeLast(2).toInt() > 59) ceil(this.toDouble()).toInt().toString()
    else this.toDouble().toInt().toString()
} else this ?: "0"

fun receiveBroadcast(callbacks: (Intent) -> Unit): BroadcastReceiver {
    return object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            callbacks(intent)
        }
    }
}

fun Long?.createTimeCounter(onStart: (Long) -> Unit, onFinish: () -> Unit): CountDownTimer {
    val timer = object : CountDownTimer(this ?: 0, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            onStart(millisUntilFinished)
        }

        override fun onFinish() {
            onFinish()
        }
    }.start()
    return timer
}

fun String.addSpaceEveryFourDigits(): String {
    return try {
        var result = ""
        val temp = this.chunked(4)
        temp.forEach {
            result += "$it "
        }
        if (result.last().toString() == " ") result.dropLast(1) else result
    } catch (e: Exception) {
        "-"
    }
}

fun String?.ifEmptyOrNull(): String {
    return if (this.isNullOrEmpty()) "" else this
}

fun String?.ifEmptyOrNull(callback: () -> String): String {
    return if (this.isNullOrEmpty()) callback() else this
}

fun String.removeSpace() = this.replace(" ", "")