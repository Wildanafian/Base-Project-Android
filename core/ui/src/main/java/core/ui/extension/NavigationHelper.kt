package core.ui.extension

import android.app.Activity
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Intent to somewhere
 * gooTo<Destination Activity>()
 *
 * Intent to somewhere with extra
 * gooTo<Destination Activity>(putExtras(key, data))
 */
inline fun <reified T : Activity> Activity.gooTo() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Activity.gooTo(noinline argsBuilder: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply {
        argsBuilder()
    })
}

inline fun <reified T : Activity> Fragment.gooTo(noinline argsBuilder: Intent.() -> Unit) {
    requireActivity().startActivity(Intent(requireActivity(), T::class.java).apply {
        argsBuilder()
    })
}

inline fun <reified T : Activity> Activity.gooToAndClear(noinline argsBuilder: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        argsBuilder()
        finish()
    })
}

inline fun <reified T : Activity> Fragment.gooToAndClear() {
    requireActivity().startActivity(Intent(requireContext(), T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        requireActivity().finish()
    })
}

inline fun <reified T : Activity> Fragment.gooToAndClear(noinline argsBuilder: Intent.() -> Unit) {
    requireActivity().startActivity(Intent(requireContext(), T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        argsBuilder()
        requireActivity().finish()
    })
}

fun Fragment.gooTo(directions: NavDirections) = findNavController().navigate(directions)

fun Fragment.goBack() = NavHostFragment.findNavController(this).popBackStack()

fun Fragment.handleTwiceBackPress() {
    val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = Unit
    }
    requireActivity().onBackPressedDispatcher.addCallback(this, callback)
}