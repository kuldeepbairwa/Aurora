package com.kuldeep.aurora.features.newChat.presentation

import com.kuldeep.aurora.core.ui.BaseViewModel
import com.kuldeep.aurora.features.newChat.domain.usecases.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewChatViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : BaseViewModel() {


    private val _uiState = MutableStateFlow(NewChatUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: NewChatUiEvent){
        when(event){
            NewChatUiEvent.GetContacts -> getContacts()
            NewChatUiEvent.NavigateUp -> TODO()
            is NewChatUiEvent.OnContactSelected -> TODO()
            NewChatUiEvent.RequestContactPermission -> TODO()
        }
    }

    private fun getContacts() {

        if (!uiState.value.contactPermissionGranted)
            return

        launchWithIoDispatcher {
            getContactsUseCase()
        }
    }
}