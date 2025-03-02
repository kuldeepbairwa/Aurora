package com.kuldeep.aurora.features.login.presentation

sealed interface LoginUiEvent {


    data class OnPhoneChange(val phone: String) : LoginUiEvent
    data object Login : LoginUiEvent

}