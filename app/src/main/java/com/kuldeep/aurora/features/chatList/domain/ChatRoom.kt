package com.kuldeep.aurora.features.chatList.domain

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoom(
    val chatRoomId: String,
    val receiverPhone: String, // Phone of the recipient
    val senderPhone: String="", // Phone of the recipient
    val name:String="", // Name of the recipient
    val profileImage:String=""
)