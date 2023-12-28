package core.ui.extension

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Wildan Nafian on 27/10/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Bundle
 * withArgs { putString(key, data) }
 */
inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply { arguments = Bundle().apply(argsBuilder) }

/**
 * Get Intent Extra
 * getExtraString(key)
 */
fun Activity.getExtraBundle(key: String): Bundle? = intent.getBundleExtra(key)
fun Activity.getExtraString(key: String): String = intent.getStringExtra(key) ?: ""
fun Activity.getExtraInt(key: String): Int = intent.getIntExtra(key, 0)
fun Activity.getExtraBool(key: String): Boolean = intent.getBooleanExtra(key, false)

fun Fragment.getExtraBundle(key: String): Bundle? = requireArguments().getBundle(key)
fun Fragment.getExtraString(key: String): String = requireArguments().getString(key, "") ?: ""
fun Fragment.getExtraInt(key: String): Int = requireArguments().getInt(key, 0)
