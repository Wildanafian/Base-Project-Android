package core.ui.extension

import java.text.NumberFormat
import java.util.Locale

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Format data to Rupiah format
 * yourStringData.formatToRupiah()
 * 10.000, 10.000,00, 10.000.00
 * result = Rp 10.000
 *
 */
fun String?.formatToRupiah(enableDecimal: Boolean? = false): String {
    return "Rp ${
        NumberFormat.getIntegerInstance(Locale.GERMANY).format(this.clearRupiahNumbering().toLong()) +
                if (enableDecimal == true) {
                    if (this?.contains(".") == true) {
                        val text = this.takeLast(this.length - this.indexOf("."))
                        when (text.length) {
                            1    -> text.replace(".", ",") + "00"
                            2    -> text.replace(".", ",") + "0"
                            else -> text.replace(".", ",")
                        }
                    } else ",00"
                } else ""
    }"
}

fun Double?.formatToRupiah(): String {
    return "Rp ${
        NumberFormat.getIntegerInstance(Locale.GERMANY).format((this ?: 0.0).toString().clearRupiahNumbering().toLong()) +
                if (this?.toString()?.contains(".") == true) this.toString().takeLast(this.toString().length - this.toString().indexOf(".")).replace(".", ",")
                else ",00"
    }"
}

/**
 * Clear rupiah format
 * yourString.clearRupiah()
 * Rp 10.000 , Rp 10.000.00, Rp 10.000,00, 10.000
 * result = 10.000
 */
fun String?.clearRupiah(): String {
    return NumberFormat.getIntegerInstance(Locale.GERMANY)
        .format(this.clearRupiahNumbering().toLong())
}

fun Int?.clearRupiah(): String {
    return NumberFormat.getIntegerInstance(Locale.GERMANY)
        .format(this.toString().clearRupiahNumbering().toLong())
}

/**
 * Clear numbering format to raw string
 * yourString.clearNumbering()
 * 10.000 , 10.000.00, 10.000,00
 * result = 10000
 */
fun String?.clearNumbering(): String = this.clearRupiahNumbering()

/**
 * Clear all rupiah format
 * yourString.clearRupiah()
 * Rp 10.000 , Rp 10.000.00, Rp 10.000,00, 10.000
 * result = 10000
 */
fun String?.clearRupiahNumbering(): String {
    val replaceable =
        String.format("[Rp,.\\s]", NumberFormat.getCurrencyInstance(getLocaleIndonesia())).toRegex()
    return try {
        if (this?.contains(".") == true || this?.contains(",") == true) {
            val temp = this.removeRange(this.indexOf("."), this.length)
            temp.replace(replaceable, "")
        } else {
            this?.replace(replaceable, "") ?: "0"
        }
    } catch (e: Exception) {
        "0"
    }
}