package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import com.kuldeep.aurora.core.data.model.MessageDTO
import com.kuldeep.aurora.core.data.model.toMessage
import com.kuldeep.aurora.core.domain.repository.DataStoreRepository
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner
import kotlinx.coroutines.flow.transform
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val repository: WebSocketRepository,
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = repository.receiveFromWebSocket()
        .transform {
            Json.decodeFromString<MessageDTO>(it)
                .apply {
                    emit(
                        toMessage(
                            if (dataStoreRepository.getUserPhone() == toPhoneNumber) MessageOwner.SENDER
                            else MessageOwner.RECEIVER
                        )
                    )
                }
        }
}