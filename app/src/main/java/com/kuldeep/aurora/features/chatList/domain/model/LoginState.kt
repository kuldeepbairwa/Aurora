package com.kuldeep.aurora.features.chatList.domain.model

enum class LoginState {
    LoggedOut,
    LoggingIn,
    LoggedIn
}

fun LoginState.isLoggedOut() = this == LoginState.LoggedOut