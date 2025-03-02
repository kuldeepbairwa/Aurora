package com.kuldeep.aurora.features.chatList.presentation

import androidx.lifecycle.ViewModel
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ChatListUiState())
    val uiState = _uiState.asStateFlow()


    // adding temporary chat room
    companion object {

        val chatRooms = buildList {

            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1234567890"
            ))
            add(ChatRoom(
                chatRoomId = "123",
                phone = "1221334434"
            ))
        }

    }

    init {
        _uiState.update { it.copy(
            chats = chatRooms
        ) }
    }

    fun onEvent(event: ChatListUiEvent) {
        when (event) {
            is ChatListUiEvent.OnChatSelected -> onChatSelected(event.chatRoom)
            is ChatListUiEvent.SearchChat -> searchChat(event.query)
            ChatListUiEvent.NewChat -> startNewChat()
            ChatListUiEvent.NavigateUp -> navigateUp()
        }
    }

    private fun searchChat(query: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchResult = currentState.chats.filter { it.phone.contains(query) }
            )
        }
    }

    private fun startNewChat() {

    }

    private fun navigateUp() {

    }

    private fun onChatSelected(chatRoom: ChatRoom) {


    }
}