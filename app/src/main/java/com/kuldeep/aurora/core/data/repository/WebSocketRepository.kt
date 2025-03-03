package com.kuldeep.aurora.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

interface WebSocketRepository {

    suspend fun connectToWebSocket(chatId:String)

    suspend fun isConnected(): Flow<Boolean>

    suspend fun sendToWebSocket(message: String)

    suspend fun receiveFromWebSocket(): Flow<String>

    suspend fun disconnectConnection()
}