package com.luisfagundes.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.luisfagundes.extensions.hideVisibility
import com.luisfagundes.extensions.showVisibility

abstract class BaseFragment<Binding : ViewBinding>(
    @IdRes private val successViewId: Int,
    @IdRes private val loadingViewId: Int,
    @IdRes private val errorViewId: Int
) : Fragment() {

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

        setupSuccess(view)
        setupLoading(view)
        setupError(view)

        binding.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun onBind(): Binding

    abstract fun Binding.onViewCreated()

    private fun setupSuccess(view: View) {
        successView = view.findViewById(successViewId)
    }

    private fun setupLoading(view: View) {
        loadingView = view.findViewById(loadingViewId)
    }

    private fun setupError(view: View) {
        errorView = view.findViewById(errorViewId)
    }

    open fun showSuccess() {
        successView.showVisibility()
        errorView.hideVisibility()
        loadingView.hideVisibility()
    }

    open fun showLoading() {
        loadingView.showVisibility()
        errorView.hideVisibility()
        successView.hideVisibility()
    }

    open fun showError() {
        errorView.showVisibility()
        successView.hideVisibility()
        loadingView.hideVisibility()
    }

    open fun setupUpActionButton() =
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
