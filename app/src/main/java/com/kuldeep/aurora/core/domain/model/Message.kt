package com.kuldeep.aurora.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val formPhoneNumber: String,
    val toPhoneNumber:String,
    val message: String
)
