package com.kuldeep.aurora.core.data.model

import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner
import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    val formPhoneNumber: String,
    val toPhoneNumber:String,
    val message: String
)

fun MessageDTO.toMessage(messageOwner: MessageOwner): Message{
    return Message(
        senderId = formPhoneNumber,
        receiverId = toPhoneNumber,
        message = message,
        messageOwner = messageOwner
    )
}