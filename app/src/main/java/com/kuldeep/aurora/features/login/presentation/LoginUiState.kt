package com.kuldeep.aurora.features.login.presentation

data class LoginUiState(
    val phone: String = "",
    val receiverPhone: String = "",
    val isLoggedIn:Boolean = false
)
