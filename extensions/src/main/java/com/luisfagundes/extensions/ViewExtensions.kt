package com.luisfagundes.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.hideVisibility() {
    this.visibility = GONE
}

fun View.showVisibility() {
    this.visibility = VISIBLE
}
