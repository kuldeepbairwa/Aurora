package com.kuldeep.aurora.core.domain.repository

import com.kuldeep.aurora.core.data.repository.WebSocketRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable
import javax.inject.Inject

class WebSocketRepoImpl @Inject constructor(
    private val client: HttpClient
) : WebSocketRepository {

    private var connection: DefaultClientWebSocketSession? = null

    override suspend fun connectToWebSocket() {

        if (connection?.isActive == true) return

        client.webSocket(
            method = HttpMethod.Companion.Get,
            host = BASE_URL
        ) {
            connection = this
        }

    }

    override suspend fun isConnected(): Flow<Boolean> {
        return flow {
            emit(connection?.isActive == true)
        }
    }

    override suspend fun sendToWebSocket(message: Serializable) {
        connection?.sendSerialized(message)
    }

    override suspend fun receiveFromWebSocket(): Flow<Serializable> {
        return flow {
            connection?.apply {
                val message = receiveDeserialized<Serializable>()
                emit(message)
            }
        }
    }

    companion object {
        const val API_KEY =
            "kZLNlHLWqcW529bcmszsmJ06GD8C2WRQ45bhZNk7" //always store your api keys securely
        const val BASE_URL = "wss://s14179.blr1.piesocket.com/v3/1?api_key=$API_KEY&notify_self=1"
    }
}