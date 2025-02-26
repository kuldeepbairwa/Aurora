package com.kuldeep.aurora.core.domain

import com.kuldeep.aurora.core.data.WebSocketRepository
import io.ktor.client.HttpClient
import javax.inject.Inject

class WebSocketRepoImpl @Inject constructor(
    private val client: HttpClient
) : WebSocketRepository {

    override suspend fun connectToWebsocket() {

    }
}