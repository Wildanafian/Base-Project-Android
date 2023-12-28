package core.common.util

import android.annotation.SuppressLint
import android.view.*
import android.widget.*
import core.common.util.ProjectConstant.DD_MM_YYYY
import core.common.util.ProjectConstant.MM_SS
import core.common.util.ProjectConstant.SERVER_DATE_FORMAT
import core.common.util.ProjectConstant.YYYY_MM_DD
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Date Formater
 *
 * Get current date
 * getCurrentDate()
 *
 * Get current date with format
 * getCurrentDateWithFormat(your format)
 *
 * You can also get current time with getCurrentDateWithFormat()
 * getCurrentDateWithFormat("HH:mm:ss")
 *
 * Get date before/after current date
 * getDateBeforeCurrentDate(how many day)
 *
 * Change current string date to other date format
 * 21 01 2022.formatDateWith("dd MM yyyy", "MM-yyyy")
 * result = 01-2022
 **/

fun getStartTime(): String = getCurrentDateWithFormat(YYYY_MM_DD) + " 00:00:01"
fun getStartTime90DaysBefore(): String = "2022-01-05 00:00:01"

fun getEndTime(): String = getCurrentDateWithFormat(YYYY_MM_DD) + " 23:59:59"

fun getLocaleIndonesia() = Locale("id", "ID")

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String = doMagic(DD_MM_YYYY, Calendar.getInstance().time)

fun getCurrentDateWithFormat(expectedPattern: String): String =
    doMagic(expectedPattern, Calendar.getInstance().time)

fun getDateBeforeCurrentDate(number: Int): String = doMagic(DD_MM_YYYY, doMagicCalendar(-number))

fun getDateAfterCurrentDate(number: Int): String = doMagic(DD_MM_YYYY, doMagicCalendar(number))

fun getDateBeforeCurrentDate(expectedPattern: String, number: Int): String =
    doMagic(expectedPattern, doMagicCalendar(-number))

fun getDateAfterCurrentDate(expectedPattern: String, number: Int): String =
    doMagic(expectedPattern, doMagicCalendar(number))

fun String?.formatDateWith(currentPattern: String, expectedPattern: String): String = try {
    doMagic(expectedPattern, SimpleDateFormat(currentPattern, getLocaleIndonesia()).parse(this ?: "")!!)
} catch (e: Exception) {
    ""
}

fun String.toDate(currentDateFormat: String): Date? {
    val dateFormat = SimpleDateFormat(currentDateFormat, getLocaleIndonesia())
    return try {
        dateFormat.parse(this)
    } catch (e: Exception) {
        null
    }
}

private fun doMagic(pattern: String, inputDate: String?): String {
    return try {
        SimpleDateFormat(pattern, getLocaleIndonesia()).format(inputDate)
    } catch (e: Exception) {
        ""
    }
}

fun doMagic(pattern: String, inputDate: Date): String {
    return try {
        SimpleDateFormat(pattern, getLocaleIndonesia()).format(inputDate)
    } catch (e: Exception) {
        ""
    }
}

private fun doMagicCalendar(number: Int): Date {
    val c = Calendar.getInstance()
    c.add(Calendar.DATE, number)
    return c.time
}

fun getTimeStamp() = getCurrentDateWithFormat(SERVER_DATE_FORMAT)

fun Long.millisToMmSs() = doMagic(MM_SS, Date(this))
fun String?.toSeconds() = (this?.toLong() ?: 0) * 1000