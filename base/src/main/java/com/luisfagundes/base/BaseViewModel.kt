package com.luisfagundes.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun ViewModel.executeCoroutines(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(dispatcher, block = action)

}

