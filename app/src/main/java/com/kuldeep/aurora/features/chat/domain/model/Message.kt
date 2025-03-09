package com.kuldeep.aurora.features.chat.domain.model

import com.kuldeep.aurora.core.data.model.MessageDTO

data class Message(
    val senderId: String,
    val receiverId:String,
    val message: String,
    val messageOwner: MessageOwner
)

fun Message.toMessageDTO(): MessageDTO {
    return MessageDTO(
        formPhoneNumber = senderId,
        toPhoneNumber = receiverId,
        message = message
    )
}

enum class MessageOwner{
    SENDER,RECEIVER
}