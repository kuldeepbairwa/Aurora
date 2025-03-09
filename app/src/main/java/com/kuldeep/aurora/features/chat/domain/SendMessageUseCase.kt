package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner
import com.kuldeep.aurora.features.chat.domain.model.toMessageDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: WebSocketRepository,
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(message: String,receiver:String) {

        if (message.isEmpty()) return
        repository.sendToWebSocket(
            Json.encodeToString(
                Message(
                    senderId = dataStoreRepository.getUserPhone(),
                    receiverId = receiver,
                    message = message,
                    messageOwner = MessageOwner.SENDER
                ).toMessageDTO()
            )
        )
    }
}