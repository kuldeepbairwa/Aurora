package com.kuldeep.aurora.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    suspend fun connectToWebSocket(chatId:String)

    suspend fun isConnected(): Flow<Boolean>

    suspend fun sendToWebSocket(message: String)

    suspend fun receiveFromWebSocket(): Flow<String>

    suspend fun disconnectConnection()
}