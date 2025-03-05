package com.kuldeep.aurora.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  BaseViewModel
 *
 *  An abstract base class for ViewModels, providing common functionality
 *  related to coroutines and dispatchers. It simplifies launching coroutines
 *  in different contexts and switching between them.
 */
abstract class BaseViewModel : ViewModel() {

    protected val mainDispatcher = Dispatchers.Main
    protected val ioDispatchers = Dispatchers.IO
    protected val defaultDispatchers = Dispatchers.Default

    inline fun launchWithMainDispatcher(crossinline block: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            block()
        }
    }

    inline fun launchWithIODispatcher(crossinline block: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block()
        }
    }

    inline fun launchWithDefaultDispatcher(crossinline block: () -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            block()
        }
    }

    suspend inline fun withMainContext(crossinline block: () -> Unit){
        withContext(Dispatchers.Main){
            block()
        }
    }

}