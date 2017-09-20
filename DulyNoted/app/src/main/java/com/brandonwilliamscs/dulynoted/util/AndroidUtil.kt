package com.brandonwilliamscs.dulynoted.util

import android.util.DisplayMetrics

/**
 * Created by Brandon on 9/20/2017.
 */
object AndroidUtil {
    fun computeDeviceWidthDp(displayMetrics: DisplayMetrics): Float
            = displayMetrics.widthPixels / displayMetrics.density
}
