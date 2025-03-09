package com.kuldeep.aurora.features.newChat.presentation

import com.kuldeep.aurora.features.newChat.domain.model.Contact

sealed interface NewChatUiEvent {

    data class OnContactSelected(val contact: Contact) : NewChatUiEvent
    data object RequestContactPermission : NewChatUiEvent
    data object GetContacts : NewChatUiEvent
    data object NavigateUp : NewChatUiEvent
}