package com.kuldeep.aurora.features.chatList.domain

data class ChatRoom(
    val chatRoomId: String,
    val phone: String, // Phone of the recipient
    val name:String="", // Name of the recipient
    val profileImage:String=""
)