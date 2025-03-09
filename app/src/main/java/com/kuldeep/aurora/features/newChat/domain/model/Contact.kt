package com.kuldeep.aurora.features.newChat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id:Long,
    val name: String,
    val phoneNumber: String
)