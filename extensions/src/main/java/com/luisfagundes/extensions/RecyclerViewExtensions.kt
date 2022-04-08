package com.luisfagundes.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupDefaultConfig(
    orientation: Int = RecyclerView.HORIZONTAL,
    hasFixedSize: Boolean = true
) {
    val layoutManager = LinearLayoutManager(
        context,
        orientation,
        false
    )

    setHasFixedSize(hasFixedSize)
    this.layoutManager = layoutManager
}

