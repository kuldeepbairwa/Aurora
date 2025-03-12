package com.kuldeep.aurora.features.chatList.presentation

import com.kuldeep.aurora.features.chatList.domain.model.LoginState
import com.kuldeep.aurora.features.newChat.domain.model.Contact

data class ChatListUiState(
    val chats: List<Contact> = emptyList(),
    val searchResult: List<Contact> = emptyList(),
    val searchQuery: String = "",
    val newChat: Boolean = false,
    val loginState: LoginState = LoginState.LoggingIn,
    val openChat: Contact? = null,
    val popUpExpanded: Boolean = false,
)
