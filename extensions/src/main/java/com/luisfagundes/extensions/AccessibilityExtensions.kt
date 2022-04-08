package com.luisfagundes.extensions

import android.view.View
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat.setAccessibilityDelegate
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat

inline fun <reified T : View> View.readAs(
    crossinline also: AccessibilityNodeInfoCompat.() -> Unit = {}
): View {
    setAccessibilityDelegate(this, object : AccessibilityDelegateCompat() {
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfoCompat?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            info?.className = T::class.java.name
            info?.also()
        }
    })
    return this
}