package com.kuldeep.aurora.features.chatList.presentation

import androidx.lifecycle.ViewModel
import com.kuldeep.aurora.core.ui.BaseViewModel
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.features.chatList.domain.GetLoggedInUserUseCase
import com.kuldeep.aurora.features.chatList.domain.LogoutUseCase
import com.kuldeep.aurora.features.chatList.domain.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ChatListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchWithIoDispatcher {

            getLoggedInUserUseCase().collect { phone ->
                _uiState.update { currentState ->
                    currentState.copy(
                        loginState = if (phone.isEmpty()) LoginState.LoggedOut
                        else LoginState.LoggedIn
                    )
                }
            }

        }
    }


    init {
        _uiState.update {
            it.copy(
                chats = chatRooms
            )
        }
    }

    fun onEvent(event: ChatListUiEvent) {
        when (event) {
            is ChatListUiEvent.OnChatSelected -> onChatSelected(event.chatRoom)
            is ChatListUiEvent.SearchChat -> searchChat(event.query)
            ChatListUiEvent.NewChat -> startNewChat()
            ChatListUiEvent.NavigateUp -> navigateUp()
            ChatListUiEvent.ClearOpenChat -> clearOpenChat()
            is ChatListUiEvent.PopupMenu -> handlePopUpMenu(event.expanded)
            ChatListUiEvent.LogOut -> logoutUser()
        }
    }

    private fun logoutUser() {
        launchWithIoDispatcher {
            logoutUseCase.invoke()
        }
    }

    private fun handlePopUpMenu(expanded: Boolean) {
        _uiState.update {
            it.copy(popUpExpanded = expanded)
        }
    }

    private fun clearOpenChat() {
        _uiState.update { it.copy(openChat = null) }
    }

    private fun searchChat(query: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchResult = currentState.chats.filter { it.receiverPhone.contains(query) }
            )
        }
    }

    fun getUserPhone() {

    }

    private fun startNewChat() {

    }

    private fun navigateUp() {

    }

    private fun onChatSelected(chatRoom: ChatRoom) {

        _uiState.update {
            it.copy(openChat = chatRoom)
        }

    }


    // adding temporary chat room
    companion object {

        val chatRooms = buildList {

            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1234567890"
                )
            )
            add(
                ChatRoom(
                    chatRoomId = "123",
                    receiverPhone = "1221334434"
                )
            )
        }

    }
}