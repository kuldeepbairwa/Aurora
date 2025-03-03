package com.kuldeep.aurora.core.domain.model

data class Message(
    val senderId: String,
    val receiverId:String,
    val message: String,
    val messageOwner: MessageOwner
)

enum class MessageOwner{
    SENDER,RECEIVER
}