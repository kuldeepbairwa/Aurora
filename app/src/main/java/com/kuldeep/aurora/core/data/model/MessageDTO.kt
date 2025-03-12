package com.kuldeep.aurora.core.data.model

import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner
import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    val fromPhoneNumber: String,
    val toPhoneNumber:String,
    val message: String,
)

fun MessageDTO.toMessage(messageOwner: MessageOwner): Message{
    return Message(
        senderId = fromPhoneNumber,
        receiverId = toPhoneNumber,
        message = message,
        messageOwner = messageOwner,
        timeStamp = System.currentTimeMillis()
    )
}