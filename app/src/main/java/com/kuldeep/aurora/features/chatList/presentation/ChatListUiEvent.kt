package com.kuldeep.aurora.features.chatList.presentation

import com.kuldeep.aurora.features.chatList.domain.ChatRoom

sealed interface ChatListUiEvent {

    data class OnChatSelected(val chatRoom: ChatRoom):ChatListUiEvent
    data object NewChat:ChatListUiEvent
    data class SearchChat(val query:String):ChatListUiEvent
    data object NavigateUp:ChatListUiEvent
}