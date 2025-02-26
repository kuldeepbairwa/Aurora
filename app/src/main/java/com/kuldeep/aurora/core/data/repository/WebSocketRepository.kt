package com.kuldeep.aurora.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

interface WebSocketRepository {

    suspend fun connectToWebSocket(chatId:String)

    suspend fun isConnected(): Flow<Boolean>

    suspend fun sendToWebSocket(message: Serializable)

    suspend fun receiveFromWebSocket(): Flow<Serializable>

    suspend fun disconnectConnection()
}