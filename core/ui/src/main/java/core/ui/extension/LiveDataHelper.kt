package core.ui.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

/**
 **************************************
 */

/**
 * Created by Ian Damping on 21,February,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun <T> Fragment.observeEvent(data: LiveData<Event<T>>, onBound: ((T) -> Unit)) {
    data.observe(this.viewLifecycleOwner, EventObserver {
        onBound.invoke(it)
    })
}

fun <T> Fragment.observe(data: LiveData<T>, onBound: ((T?) -> Unit)) {
    data.observe(this.viewLifecycleOwner) {
        onBound.invoke(it)
    }
}

fun <T> Fragment.observeText(data: LiveData<T>, onBound: ((T) -> Unit)) {
    data.observe(this.viewLifecycleOwner) {
        onBound.invoke(it)
    }
}