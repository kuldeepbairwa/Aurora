package com.kuldeep.aurora.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val formPhoneNumber: Long,
    val toPhoneNumber:Long,
    val message: String
)
