package com.kuldeep.aurora.features.chatList.presentation

import com.kuldeep.aurora.features.chatList.domain.ChatRoom

sealed interface ChatListUiEvent {

    data class OnChatSelected(val chatRoom: ChatRoom) : ChatListUiEvent
    data class NewChat(val openScreen: Boolean) : ChatListUiEvent
    data class SearchChat(val query: String) : ChatListUiEvent
    data object NavigateUp : ChatListUiEvent
    data object ClearOpenChat : ChatListUiEvent
    data class PopupMenu(val expanded: Boolean) : ChatListUiEvent
    data object LogOut : ChatListUiEvent
}