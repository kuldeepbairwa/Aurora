package com.kuldeep.aurora.core.domain

import com.kuldeep.aurora.core.data.WebSocketRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import javax.inject.Inject

class WebSocketRepoImpl @Inject constructor(
    private val client: HttpClient
) : WebSocketRepository {


    override suspend fun sendToWebSocket(message: Serializable) {
        client
            .webSocket(
                method = HttpMethod.Get,
                host = BASE_URL,
            ) { sendSerialized(message) }
    }

    override suspend fun receiveFromWebSocket(): Flow<Serializable> {
        return flow {
            client
                .webSocket(
                    method = HttpMethod.Get,
                    host = BASE_URL,
                ) {
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