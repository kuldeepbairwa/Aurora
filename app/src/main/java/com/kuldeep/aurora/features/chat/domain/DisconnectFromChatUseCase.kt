package com.kuldeep.aurora.features.chat.domain

import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import javax.inject.Inject

class DisconnectFromChatUseCase @Inject constructor(
    private val repository: WebSocketRepository
) {
    suspend operator fun invoke(){
        repository.disconnectConnection()
    }
}