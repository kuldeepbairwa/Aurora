package com.kuldeep.aurora.features.chat.presentation

import androidx.lifecycle.ViewModel
import com.kuldeep.aurora.core.domain.model.Message
import com.kuldeep.aurora.features.chat.domain.ConnectToChatRoomUseCase
import com.kuldeep.aurora.features.chat.domain.ReceiveMessageUseCase
import com.kuldeep.aurora.features.chat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val receiveMessageUseCase: ReceiveMessageUseCase,
    private val connectToChatRoomUseCase: ConnectToChatRoomUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ChatUiEvents){
        when(event){
            is ChatUiEvents.MessageChange -> {
                _uiState.update {
                    it.copy(message = event.message)
                }
            }
            ChatUiEvents.SendMessageClick -> {
                sendMessage(_uiState.value.message)
            }
        }
    }

    private fun sendMessage(message: String) {
        sendMessageUseCase(
            message = Message(
                senderId =
            )
        )
    }
}