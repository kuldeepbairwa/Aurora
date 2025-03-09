package com.kuldeep.aurora.features.chat.presentation

import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.newChat.domain.model.Contact

data class ChatUiState(
    val contact: Contact? = null,
    val messages: List<Message> = emptyList(),
    val message:String=""
)