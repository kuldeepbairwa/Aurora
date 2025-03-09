package com.kuldeep.aurora.features.login.presentation

import androidx.lifecycle.ViewModel
import com.kuldeep.aurora.core.ui.BaseViewModel
import com.kuldeep.aurora.features.login.domain.SetUserPhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setUserPhoneUseCase: SetUserPhoneUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnPhoneChange -> {
                _uiState.update { it.copy(phone = event.phone) }
            }

            is LoginUiEvent.Login -> {

//              custom validation logic can be placed here
                if (uiState.value.phone.isNotEmpty()) {
                    launchWithIoDispatcher {
                        setUserPhoneUseCase(uiState.value.phone)
                        _uiState.update { it.copy(isLoggedIn = true) }
                    }
                }
            }

            LoginUiEvent.ResetState -> {
                _uiState.update {
                    LoginUiState()
                }
            }
        }
    }

}