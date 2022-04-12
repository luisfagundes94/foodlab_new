package com.luisfagundes.extensions

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.luisfagundes.model.PicassoModel
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) = Picasso
    .get()
    .load(url)
    .into(this)

fun ImageView.loadPoster(picassoModel: PicassoModel) = Picasso
    .get()
    .load(picassoModel.url)
    .placeholder(picassoModel.placeholderId)
    .error(picassoModel.errorId)
    .into(this)


fun ImageView.setCustomLowerCorners(curveRadius: Float = 40F) {
    this.outlineProvider = object : ViewOutlineProvider() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, -curveRadius.toInt(), view!!.width, view.height, curveRadius)
        }
    }
    this.clipToOutline = true
}
