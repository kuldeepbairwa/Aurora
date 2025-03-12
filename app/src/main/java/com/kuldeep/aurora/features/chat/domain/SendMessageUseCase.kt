package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.data.model.MessageDTO
import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
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
                MessageDTO(
                    fromPhoneNumber = dataStoreRepository.getUserPhone().replace(" ",""),
                    toPhoneNumber = receiver.replace(" ",""),
                    message = message,
                )
            )
        )
    }
}