package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.data.repository.WebSocketRepository
import com.kuldeep.aurora.core.data.model.MessageDTO
import kotlinx.coroutines.flow.transform
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val repository: WebSocketRepository
) {
    suspend operator fun invoke() = repository.receiveFromWebSocket()
        .transform {
           Json.decodeFromString<MessageDTO>(it)
               .apply { emit(this) }
        }
}