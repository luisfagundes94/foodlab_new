package com.luisfagundes.extensions

import android.util.DisplayMetrics
import android.view.Display
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber
import kotlin.math.roundToInt

fun <F : Fragment> F.requireString(any: Any?) = requireContext().requireString(any)

fun <F : Fragment, L> F.observe(
    liveData: LiveData<L>,
    onChanged: L.() -> Unit
) = liveData.observe(viewLifecycleOwner, onChanged)

fun NavController.navigateWithDirections(navDirections: NavDirections) {
    try {
        navigate(navDirections)
    } catch (exception: Exception) {
        Timber.e(exception)
    }
}

fun NavController.navigateWithDeepLink(
    deepLinkDestination: String
) {
    try {
        navigate(deepLinkDestination.toUri())
    } catch (exception: Exception) {
        Timber.e(exception)
    }
}

fun Fragment.getColor(id: Int) = ContextCompat.getColor(requireContext(), id)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), message, duration).show()

fun Fragment.showToast(messageList: List<CharSequence>, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), messageList.toString(), duration).show()

fun Fragment.getBestGridSpanCount(posterWidth: Int): Int {
    val display: Display = requireActivity().windowManager.defaultDisplay
    val outMetrics = DisplayMetrics()
    display.getMetrics(outMetrics)
    val screenWidth = outMetrics.widthPixels.toFloat()
    return (screenWidth / posterWidth).roundToInt()
}
