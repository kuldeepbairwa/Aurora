package com.kuldeep.aurora.core.data

interface WebSocketRepository {
    suspend fun connectToWebsocket()
}