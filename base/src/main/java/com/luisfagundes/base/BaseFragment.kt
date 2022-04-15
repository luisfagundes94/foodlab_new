package com.luisfagundes.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.luisfagundes.extensions.hideVisibility
import com.luisfagundes.extensions.showVisibility

abstract class BaseFragment<Binding : ViewBinding>: Fragment() {

    private lateinit var viewFlipper: ViewFlipper
    private lateinit var successView: ViewGroup
    private lateinit var loadingView: ViewGroup
    private lateinit var errorView: ViewGroup

    private var _binding: Binding? = null
    val binding: Binding
        get() = _binding ?: run {
            _binding = onBind()
            binding
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun onBind(): Binding

    abstract fun Binding.onViewCreated()

    companion object {
        const val FLIPPER_CHILD_LOADING = 0
        const val FLIPPER_CHILD_ERROR = 1
        const val FLIPPER_CHILD_SUCCESS = 2
    }
}
