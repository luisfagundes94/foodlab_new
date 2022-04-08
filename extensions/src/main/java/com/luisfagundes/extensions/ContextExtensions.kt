package com.luisfagundes.extensions

import android.content.Context
import android.content.res.Resources

fun Context.requireString(any: Any?): String = when (any) {
    null -> String.empty()
    is Int -> try {
        getString(any)
    } catch (exception: Resources.NotFoundException) {
        any.toString()
    }
    else -> any.toString()
}
