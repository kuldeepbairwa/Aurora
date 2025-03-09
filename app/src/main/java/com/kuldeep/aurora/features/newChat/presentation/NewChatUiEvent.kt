package com.kuldeep.aurora.features.newChat.presentation

import com.kuldeep.aurora.features.newChat.domain.model.Contact

sealed interface NewChatUiEvent {

    data class OnContactSelected(val contact: Contact) : NewChatUiEvent
    data class RequestContactPermission(val granted: Boolean) : NewChatUiEvent
    data class ContactPermissionResult(val granted: Boolean) : NewChatUiEvent
    data object GetContacts : NewChatUiEvent
}