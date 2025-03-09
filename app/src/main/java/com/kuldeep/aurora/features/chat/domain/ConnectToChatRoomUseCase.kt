package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import javax.inject.Inject

class ConnectToChatRoomUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val repository: WebSocketRepository
) {
    suspend operator fun invoke(contact: Contact){
        val chatRoomId = listOf(
            dataStoreRepository.getUserPhone(),
            contact.phoneNumber
        ).sorted().joinToString()
            .replace(" ", "")
            .replace(",","")
        repository.connectToWebSocket(chatRoomId)
    }
}