package com.kuldeep.aurora.features.chat.domain.model

data class Message(
    val senderId: String,
    val receiverId:String,
    val message: String,
    val messageOwner: MessageOwner,
    val timeStamp : Long
)

enum class MessageOwner{
    SENDER,RECEIVER
}