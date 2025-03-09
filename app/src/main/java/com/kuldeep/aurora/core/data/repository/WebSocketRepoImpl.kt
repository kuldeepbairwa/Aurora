package com.kuldeep.aurora.core.data.repository

import android.util.Log
import com.kuldeep.aurora.BuildConfig
import com.kuldeep.aurora.core.domain.repository.WebSocketRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebSocketRepoImpl @Inject constructor(
    private val client: HttpClient
) : WebSocketRepository {

    private var connection: DefaultClientWebSocketSession? = null

    private val _messagesFlow = MutableSharedFlow<String>(replay = 1)
    val messagesFlow: SharedFlow<String> = _messagesFlow.asSharedFlow()

    override suspend fun connectToWebSocket(chatId: String) {
        Log.d("WebSocket", "Connecting to chat: $chatId")
        if (connection?.isActive == true) {
            Log.d("WebSocket", "Already connected")
            return
        }

        try {
            client.webSocket(
                urlString = getBaseUrl(chatId)
            ) {
                connection = this
                Log.d("WebSocket", "Connected successfully")
                receiveMessages()
            }
        } catch (e: Exception) {
            Log.e("WebSocket", "Connection failed: ${e.message}")
        }
    }

    override suspend fun isConnected(): Flow<Boolean> {
        return flow {
            emit(connection?.isActive == true)
        }
    }

    override suspend fun sendToWebSocket(message: String) {
        Log.d("WebSocket", "Sending message: $message")
        try {
            connection?.send(Frame.Text(message))
        } catch (e: Exception) {
            Log.e("WebSocket", "Failed to send message: ${e.message}")
        }
    }

    override suspend fun receiveFromWebSocket(): Flow<String> {
        return messagesFlow
    }

    private suspend fun DefaultClientWebSocketSession.receiveMessages() {
        while (isActive) {
            try {
                val frame = incoming.receive()
                if (frame is Frame.Text) {
                    val message = frame.readText()
                    Log.d("WebSocket", "Received message: $message")
                    _messagesFlow.emit(message)
                }
            } catch (e: Exception) {
                Log.e("WebSocket", "Receiving failed: ${e.message}")
                delay(2000) // Prevents rapid retries
            }
        }
    }

    override suspend fun disconnectConnection() {
        try {
            connection?.close()
            connection = null
            Log.d("WebSocket", "Disconnected")
        } catch (e: Exception) {
            Log.e("WebSocket", "Failed to disconnect: ${e.message}")
        }
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY // Store API keys securely!

        fun getBaseUrl(chatId: String): String {
            return "wss://s14179.blr1.piesocket.com/v3/$chatId?api_key=$API_KEY&notify_self=1"
        }
    }
}