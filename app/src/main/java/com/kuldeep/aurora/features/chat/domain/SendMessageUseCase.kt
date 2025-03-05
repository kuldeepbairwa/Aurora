package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import com.kuldeep.aurora.core.domain.model.Message
import com.kuldeep.aurora.core.domain.model.toMessageDTO
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: WebSocketRepository
) {

    suspend operator fun invoke(message: Message){
        repository.sendToWebSocket(Json.encodeToString(message.toMessageDTO()))
    }
}