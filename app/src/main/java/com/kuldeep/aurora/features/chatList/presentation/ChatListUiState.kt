package com.kuldeep.aurora.features.chatList.presentation

import com.kuldeep.aurora.features.chatList.domain.ChatRoom

data class ChatListUiState(
    val chats: List<ChatRoom> = emptyList(),
    val selectedChat: ChatRoom? = null,
    val searchResult: List<ChatRoom> = emptyList(),
    val searchQuery: String = "",
    val newChat:Boolean = false
)
