package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.data.repository.WebSocketRepository
import com.kuldeep.aurora.core.data.model.MessageDTO
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: WebSocketRepository
) {

    suspend operator fun invoke(messageDTO: MessageDTO){
        repository.sendToWebSocket(Json.encodeToString(messageDTO))
    }
}