package com.kuldeep.aurora.features.chatList.presentation

import com.kuldeep.aurora.core.ui.BaseViewModel
import com.kuldeep.aurora.features.chatList.domain.GetLoggedInUserUseCase
import com.kuldeep.aurora.features.chatList.domain.LogoutUseCase
import com.kuldeep.aurora.features.chatList.domain.model.LoginState
import com.kuldeep.aurora.features.newChat.domain.model.Contact
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

            getLoggedInUserUseCase().collect { phoneNumber ->
                _uiState.update { currentState ->
                    currentState.copy(
                        loginState = if (phoneNumber.isEmpty()) LoginState.LoggedOut
                        else LoginState.LoggedIn
                    )
                }
            }

        }
    }


    init {
        _uiState.update {
            it.copy(
                chats = Contacts
            )
        }
    }

    fun onEvent(event: ChatListUiEvent) {
        when (event) {
            is ChatListUiEvent.OnChatSelected -> onChatSelected(event.contact)
            is ChatListUiEvent.SearchChat -> searchChat(event.query)
            is ChatListUiEvent.NewChat -> startNewChat(event.openScreen)
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
                searchResult = currentState.chats.filter { it.name.contains(query) }
            )
        }
    }

    fun getUserphoneNumber() {

    }

    private fun startNewChat(openScreen: Boolean) {

        _uiState.update { it.copy(newChat = openScreen) }
    }

    private fun navigateUp() {

    }

    private fun onChatSelected(contact: Contact) {

        _uiState.update {
            it.copy(openChat = contact)
        }

    }


    // adding temporary chat room
    companion object {

        val Contacts = buildList {

            add(
                Contact(
                    name = "Kuldeep",
                    phoneNumber = "1234567890"
                )
            )
            add(
                Contact(
                    name = "Lalit",
                    phoneNumber = "1221334434"
                )
            )
            add(
                Contact(
                    name = "Asha",
                    phoneNumber = "1234567890"
                )
            )
            add(
                Contact(
                    name = "Minakshi",
                    phoneNumber = "1221334434"
                )
            )
            add(
                Contact(
                    name = "Deepak",
                    phoneNumber = "1234567890"
                )
            )
            add(
                Contact(
                    name = "R unc",
                    phoneNumber = "1221334434"
                )
            )
            add(
                Contact(
                    name = "T unc",
                    phoneNumber = "1234567890"
                )
            )
            add(
                Contact(
                    name = "K unc",
                    phoneNumber = "1221334434"
                )
            )
        }

    }
}