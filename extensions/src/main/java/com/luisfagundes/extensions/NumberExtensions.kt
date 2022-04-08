package com.luisfagundes.extensions

import android.content.res.Resources

private const val HALF_DENSITY = 0.5f

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + HALF_DENSITY).toInt()

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + HALF_DENSITY).toInt()