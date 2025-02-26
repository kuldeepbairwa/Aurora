package com.kuldeep.aurora.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

interface WebSocketRepository {

    suspend fun sendToWebSocket(message: Serializable)

    suspend fun receiveFromWebSocket(): Flow<Serializable>
}