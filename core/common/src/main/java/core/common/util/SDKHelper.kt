package core.common.util

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

/**
 * Created by Wildan Nafian on 25/09/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun isSdkAboveOrAtLeastAndroidO(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
fun isSdkAboveOrAtLeastAndroidM(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isSdkAboveOrAtLeastAndroidTIRAMISU(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU