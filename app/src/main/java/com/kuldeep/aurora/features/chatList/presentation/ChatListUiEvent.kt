package com.kuldeep.aurora.features.chatList.presentation

import com.kuldeep.aurora.features.newChat.domain.model.Contact

sealed interface ChatListUiEvent {

    data class OnChatSelected(val contact: Contact) : ChatListUiEvent
    data class NewChat(val openScreen: Boolean) : ChatListUiEvent
    data class SearchChat(val query: String) : ChatListUiEvent
    data object NavigateUp : ChatListUiEvent
    data object ClearOpenChat : ChatListUiEvent
    data class PopupMenu(val expanded: Boolean) : ChatListUiEvent
    data object LogOut : ChatListUiEvent
}