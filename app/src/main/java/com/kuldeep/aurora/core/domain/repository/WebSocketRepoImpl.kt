package com.kuldeep.aurora.core.domain.repository

import com.kuldeep.aurora.core.data.repository.WebSocketRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.close
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable
import javax.inject.Inject

class WebSocketRepoImpl @Inject constructor(
    private val client: HttpClient
) : WebSocketRepository {

    private var connection: DefaultClientWebSocketSession? = null

    override suspend fun connectToWebSocket(chatId: String) {
        if (connection?.isActive == true) return

        client.webSocket(
            method = HttpMethod.Get,
            host = getBaseUrl(chatId)
        ) {
            connection = this
        }

    }

    override suspend fun isConnected(): Flow<Boolean> {
        return flow {
            emit(connection?.isActive == true)
        }
    }

    override suspend fun sendToWebSocket(message: String) {
        connection?.sendSerialized(message)
    }

    override suspend fun receiveFromWebSocket(): Flow<String> {
        return flow {
            connection?.receiveDeserialized<String>()?.let { emit(it) }
        }
    }

    override suspend fun disconnectConnection() {
        connection?.close()
    }

    companion object {
        private const val API_KEY =
            "kZLNlHLWqcW529bcmszsmJ06GD8C2WRQ45bhZNk7" //always store your api keys securely

        fun getBaseUrl(chatId: String): String {
            return "wss://s14179.blr1.piesocket.com/v3/$chatId?api_key=$API_KEY&notify_self=1"
        }
    }
}