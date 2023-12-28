package core.datastore.helper

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

/**
 * Created by Wildan Nafian on 25/09/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
internal fun isSdkAboveOrAtLeastAndroidM(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M