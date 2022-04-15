package com.luisfagundes.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BasePagingViewModel : ViewModel() {

    protected fun ViewModel.executeCoroutines(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(dispatcher, block = action)

    fun getPageConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )

    private companion object {
        const val PAGE_SIZE = 10
    }
}