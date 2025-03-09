package com.kuldeep.aurora.features.chat.presentation

import android.util.Log
import com.kuldeep.aurora.core.ui.BaseViewModel
import com.kuldeep.aurora.features.chat.domain.ConnectToChatRoomUseCase
import com.kuldeep.aurora.features.chat.domain.DisconnectFromChatUseCase
import com.kuldeep.aurora.features.chat.domain.ReceiveMessageUseCase
import com.kuldeep.aurora.features.chat.domain.SendMessageUseCase
import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val receiveMessageUseCase: ReceiveMessageUseCase,
    private val connectToChatRoomUseCase: ConnectToChatRoomUseCase,
    private val disconnectFromChatUseCase: DisconnectFromChatUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    init {
        receiveMessages()
    }

    fun onEvent(event: ChatUiEvents){
        when(event){
            is ChatUiEvents.MessageChange -> {
                _uiState.update {
                    it.copy(message = event.message)
                }
            }
            ChatUiEvents.SendMessageClick -> {
                sendMessage()
            }

            ChatUiEvents.DisconnectFromChatRoom -> disconnectToChatRoom()
            ChatUiEvents.ConnectToChatRoom -> connectToChatRoom()
        }
    }

    fun contactBundle(contact: Contact){
        _uiState.update {
            it.copy(contact = contact)
        }
    }

    private fun sendMessage() {
        launchWithIoDispatcher {
            try {
                sendMessageUseCase(
                    message = uiState.value.message,
                    receiver = uiState.value.contact?.phoneNumber
                        ?: throw IllegalStateException("Invalid Phone Number")
                )

                // Clear message field after successful send
                _uiState.update { it.copy(message = "") }

            } catch (e: Exception) {
                Log.e("MESSAGE SEND ERROR", e.message ?: "Unknown error")
            }
        }
    }


    private fun receiveMessages() {

        launchWithIoDispatcher {
            receiveMessageUseCase().collect { message ->
                _uiState.update { currentState ->
                    currentState.copy(messages = currentState.messages.plus(message))
                }
            }
        }

    }

    private fun connectToChatRoom() {

        uiState.value.contact?.let {
            launchWithIoDispatcher {
                connectToChatRoomUseCase(it)
            }
        }
    }

    private fun disconnectToChatRoom(){
        launchWithIoDispatcher {
            disconnectFromChatUseCase()
        }
    }
}