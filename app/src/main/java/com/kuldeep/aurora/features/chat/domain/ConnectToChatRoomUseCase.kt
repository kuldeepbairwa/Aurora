package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import javax.inject.Inject

class ConnectToChatRoomUseCase @Inject constructor(
    private val repository: WebSocketRepository
) {
    suspend operator fun invoke(chatRoom: ChatRoom){
        val chatRoomId = listOf(
            chatRoom.senderPhone,
            chatRoom.receiverPhone
        ).sorted().joinToString("_")
        repository.connectToWebSocket(chatRoomId)
    }
}