package core.ui.extension

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.ref.WeakReference

/**
 * Created by Wildan Nafian on 27/10/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

const val BOBA_DELIVERY = "boba_delivery"
const val BOBA_STATUS = "status"

fun Context.broadcastBobaState(state: Boolean) {
    val weakReference = WeakReference(this)
    val intent = Intent(BOBA_DELIVERY)
    intent.putExtra(BOBA_STATUS, state)
    LocalBroadcastManager.getInstance(weakReference.get() ?: return).sendBroadcast(intent)
}

fun Context.createBroadcast(key: String, param: String, value: String) {
    val weakReference = WeakReference(this)
    val intent = Intent(key)
    intent.putExtra(param, value)
    LocalBroadcastManager.getInstance(weakReference.get() ?: return).sendBroadcast(intent)
}