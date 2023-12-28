package core.ui.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.addCallback
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import core.ui.R
import core.ui.helper.EndlessRecyclerViewScrollListener
import core.ui.helper.SafeClickListener
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.*

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Initialize RecycleView
 * yourRecycleView.initRecycleView(your adapter)
 */
fun <RV : RecyclerView.ViewHolder?> RecyclerView.initRecycleView(
        adapterRV: RecyclerView.Adapter<RV>,
        isVertical: Boolean = true,
        isReverse: Boolean = false,
        fixedSize: Boolean = false,
        cacheRv: Boolean = false,
        customLinearLayoutManager: LinearLayoutManager? = null,
) =
    this.apply {
        layoutManager = customLinearLayoutManager ?: LinearLayoutManager(
            this.context,
            if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
            isReverse
        )
        adapter = adapterRV
        setHasFixedSize(fixedSize)
        if (cacheRv) setItemViewCacheSize(25)

    }

fun LinearLayoutManager.endlessScroll(callback: () -> Unit): EndlessRecyclerViewScrollListener {
    return object : EndlessRecyclerViewScrollListener(this) {
        override fun onLoadMore(p: Int, totalItemsCount: Int, view: RecyclerView?) {
            callback()
        }
    }
}

/**
 * Load image
 * imageView.LoadImageFrom(your url)
 */
fun ImageView.loadImageFrom(imageUrl: String) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.img_no_image)
        .error(R.drawable.img_no_image)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .priority(Priority.HIGH)
        .override(111, 111)
    Glide.with(context)
        .load(imageUrl)
        .apply(options)
        .sizeMultiplier(0.25f)
        .into(this)
}

/**
 * Set color & drawable
 * setColor(R.color.red) // setDrawable(getExtraInt("icon"))
 */
fun <T : Fragment> T.setColor(color: Int): Int = ContextCompat.getColor(requireContext(), color)
fun <T : Activity> T.setColor(color: Int): Int = ContextCompat.getColor(this, color)
fun Context.setColor(color: Int): Int = ContextCompat.getColor(this, color)

fun <T : Fragment> T.setDrawable(drawable: Int): Drawable? =
    ContextCompat.getDrawable(requireContext(), drawable)

fun <T : Activity> T.setDrawable(drawable: Int): Drawable? =
    ContextCompat.getDrawable(this, drawable)

/**
 * View Enable
 * yourView.setEnable()
 */
fun View.setEnable() {
    isEnabled = true
}

fun View.setDisable() {
    isEnabled = false
}

fun View.setEnableWithColor(@DrawableRes drawable: Int) {
    isEnabled = true
    setBackgroundResource(drawable)
}

fun View.setDisableWithColor(@DrawableRes drawable: Int) {
    isEnabled = false
    setBackgroundResource(drawable)
}

fun View.setEnableDisableWhen(value: Boolean) {
    isEnabled = value
}

/**
 * Visibility
 * yourView.setVisible()
 */
fun View.setVisile() {
    visibility = View.VISIBLE
}

fun setVisile() = View.VISIBLE

fun View.setGone() {
    visibility = View.GONE
}

fun setGone() = View.GONE

fun View.setInvincible() {
    visibility = View.INVISIBLE
}

fun setInvincible() = View.INVISIBLE

fun View.setVisibilityWhen(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

fun View.setVisibilityAnimSlide(visibility: Int, duration: Long = 200) {
    val transition: Transition = Slide(Gravity.BOTTOM)
    transition.duration = duration
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.visibility = visibility
}

fun View.changeMotionLayoutChildVisibility(visibility: Boolean) {
    val motionLayout = parent as MotionLayout
    motionLayout.constraintSetIds.forEach {
        val constraintSet = motionLayout.getConstraintSet(it) ?: return@forEach
        constraintSet.setVisibility(this.id, if (visibility) View.VISIBLE else View.GONE)
        constraintSet.applyTo(motionLayout)
    }
}

typealias Inflate<T> = (LayoutInflater) -> T

fun ScrollView.scrollToBottom() {
    val lastChild = getChildAt(childCount - 1)
    val bottom = lastChild.bottom + paddingBottom
    val delta = bottom - (scrollY + height)
    smoothScrollBy(0, delta)
}

fun Toolbar.setToolbarBackNavigationAction(fragment: Fragment) {
    setNavigationOnClickListener {
        NavHostFragment.findNavController(fragment).popBackStack()
    }
}

fun View.openSoftKeyboard(context: Context) {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun Button.setBackgroundAndText(
        context: Context,
        @DrawableRes drawable: Int,
        @ColorRes color: Int,
) {
    setBackgroundResource(drawable)
    setTextColor(context.setColor(color))
}

fun TextView.setTextToGrey(status: Boolean) {
    if (status) {
        setTextColor(context.setColor(R.color.gray))
    } else {
        setTextColor(context.setColor(R.color.redhot))
    }
}

fun TextView.setTextColorAndVisibility(status: Boolean) {
    if (status) {
        this.setGone()
    } else {
        setTextColor(this.context.setColor(R.color.redhot))
        this.setVisile()
    }
}

fun TextView.setTextGreen() {
    setTextColor(context.setColor(R.color.primary))
}

fun Button.setButtonActive(status: Boolean) {
    if (status) this.setBackgroundAndText(this.context, R.drawable.bg_gradient_primary_r20, R.color.white)
    else this.setBackgroundAndText(this.context, R.drawable.bg_gray_r20, R.color.white)
    this.setEnableDisableWhen(status)
}

fun TextView.setButtonActive(state: Boolean) {
    isEnabled = state
    setTextColor(this.context.setColor(if (state) R.color.red else R.color.gray))
}

fun Button.listener(callback: () -> Unit) = this.extractedListner(callback)

fun TextView.listener(callback: () -> Unit) = this.extractedListner(callback)

fun ImageView.listener(callback: () -> Unit) = this.extractedListner(callback)

fun LinearLayout.listener(callback: () -> Unit) = this.extractedListner(callback)

fun RelativeLayout.listener(callback: () -> Unit) = this.extractedListner(callback)

fun FloatingActionButton.listener(callback: () -> Unit) = this.extractedListner(callback)

fun SwipeRefreshLayout.listener(callback: () -> Unit) = this.setOnRefreshListener {
    callback()
    this.isRefreshing = false
}

private fun View.extractedListner(callback: () -> Unit) {
    val safeClickListener = SafeClickListener {
        callback()
    }
    setOnClickListener(safeClickListener)
}

fun SwipeRefreshLayout.showSwipeRefreshLoading(state: Boolean) {
    this.isRefreshing = state
}

fun SearchView.listener(
        onTextChange: ((String) -> Unit)? = null,
        onTextSubmit: ((String) -> Unit)? = null,
) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String): Boolean {
            onTextChange?.invoke(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            onTextSubmit?.invoke(query)
            return false
        }
    })
}

fun Fragment.spinnerAdapter(data: ArrayList<String>): ArrayAdapter<String> {
    return object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, data) {
        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }
    }.apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}

fun Fragment.spinnerAdapterWithCustomLayout(data: ArrayList<String>, layout: Int): ArrayAdapter<String> =
    ArrayAdapter(this.requireContext(), layout, data).apply<ArrayAdapter<String>> {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

fun EditText.getTexts() = text.toString()

fun EditText.clearTexts() = setText("")

fun EditText.isNotEmpty() = text.toString().isNotEmpty()

fun EditText.isEmpty() = text.toString().isEmpty()

internal fun getLocaleIndonesia() = Locale("id", "ID")

fun EditText.currencyEditTextListener(locale: Locale? = null, onTextChange: ((String, Boolean) -> Unit)? = null) {
    var tempString = ""
    val replaceable = String.format("[Rp,.\\s]", NumberFormat.getCurrencyInstance(getLocaleIndonesia()))

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) = Unit
        override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
            if (s.toString() != tempString) {
                this@currencyEditTextListener.removeTextChangedListener(this)

                val cleanString = s.toString().replace(replaceable.toRegex(), "")

                val parsed = try {
                    cleanString.toDouble()
                } catch (e: NumberFormatException) {
                    0.00
                }

                val formatted = if (parsed == 0.00) {
                    ""
                } else {
                    if (locale == null) {
                        NumberFormat.getCurrencyInstance(getLocaleIndonesia()).apply {
                            maximumFractionDigits = 0
                            isParseIntegerOnly = true
                        }.format(parsed)
                    } else NumberFormat.getIntegerInstance(Locale.GERMANY).format(parsed)
                }

                onTextChange?.invoke(cleanString, parsed == 0.00)
                tempString = formatted
                this@currencyEditTextListener.setText(formatted)
                this@currencyEditTextListener.setSelection(formatted.length)
                this@currencyEditTextListener.addTextChangedListener(this)
            }
        }

        override fun afterTextChanged(editable: Editable) = Unit
    })
}

fun EditText.listener(callback: ((Int) -> Unit)? = null) {
    addTextChangedListener(onTextChanged = { count, _, _, _ ->
        callback?.invoke(count?.length ?: 0)
    })
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.listenerOtp(moveCursor: ((Boolean) -> Unit)? = null) {
    //having problem on non google keyboard
//    this.setOnKeyListener { _, keyCode, _ ->
//        if (keyCode == KeyEvent.KEYCODE_DEL)
//            Handler(Looper.getMainLooper()).postDelayed({ moveCursor?.invoke(false) }, 100)
//        if (keyCode in 7..16 && this.isNotEmpty()) moveCursor?.invoke(true)
//        false
//    }

    doOnTextChanged { _, _, _, count ->
        if (count == 1) moveCursor?.invoke(true)
        else Handler(Looper.getMainLooper()).postDelayed({ moveCursor?.invoke(false) }, 100)
    }
    setOnTouchListener { _, event ->
        this@listenerOtp.onTouchEvent(event)
        this@listenerOtp.setSelection(this@listenerOtp.text.length)
        true
    }
}

fun EditText.listenToAutoComplete(text: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(it: Editable?) {
            this@listenToAutoComplete.removeTextChangedListener(this)
            text(it.toString())
            this@listenToAutoComplete.addTextChangedListener(this)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    })
}

fun EditText.isPinValidLength(validity: (Boolean) -> Unit) {
    doOnTextChanged { text, _, _, _ ->
        validity.invoke((text?.length == 6))
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.getCheckedRadioButtonString(group: RadioGroup): String {
    val radioButton = this.findViewById<RadioButton>(group.checkedRadioButtonId)
    return radioButton.text.toString().lowercase()
}

//fun String.generateQrImage(): Bitmap {
//    val bitMatrix = MultiFormatWriter().encode(this.replace("\n", ""), BarcodeFormat.QR_CODE, 800, 800)
//    return BarcodeEncoder().createBitmap(bitMatrix)
//}

fun View.setSlideInAnimation() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in))
}

fun View.setSlideOutAnimation() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out))
}

suspend fun View.startSlideInOutAnimation() {
    if (!this.isVisible) {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in))
        setVisile()
        delay(1500)
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out))
        setGone()
    }
}

@SuppressLint("DefaultLocale")
@Suppress("DEPRECATION")
fun String.capitalizeEveryFirstWords(): String = split(" ").map { it.toLowerCase().capitalize() }.joinToString(" ")

fun Fragment.setStatusbarFull() {
    WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
    requireActivity().window?.statusBarColor = ContextCompat.getColor(requireContext(), getTransparent())
}

fun Fragment.setStatusbarNormal() {
    WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
    requireActivity().window?.statusBarColor = ContextCompat.getColor(requireContext(), getWhite())
}

fun LifecycleOwner.disableBackPressed(owner: FragmentActivity) =
    owner.onBackPressedDispatcher.addCallback(this) {}