package com.kuldeep.aurora.features.auth.login.domain

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val formPhoneNumber: Long,
    val toPhoneNumber:Long,
    val message: String
)
