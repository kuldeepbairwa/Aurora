package com.kuldeep.aurora.features.chat.presentation

sealed interface ChatUiEvents {

    data class MessageChange(val message: String) : ChatUiEvents
    data object SendMessageClick : ChatUiEvents

}