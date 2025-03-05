package com.kuldeep.aurora.features.chat.presentation

import com.kuldeep.aurora.core.domain.model.Message

data class ChatUiState(
    val chatRoomId:String="",
    val messages: List<Message> = emptyList(),
    val message:String=""
)