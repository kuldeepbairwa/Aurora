package com.kuldeep.aurora.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    val formPhoneNumber: String,
    val toPhoneNumber:String,
    val message: String
)
