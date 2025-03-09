package com.kuldeep.aurora.features.chatList.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuldeep.aurora.R
import com.kuldeep.aurora.core.ui.components.AuroraAppBar
import com.kuldeep.aurora.core.ui.components.Loader
import com.kuldeep.aurora.core.ui.components.PopupMenu
import com.kuldeep.aurora.core.ui.components.PopupMenuItem
import com.kuldeep.aurora.core.ui.components.VerticalDivider
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.features.chatList.domain.model.LoginState
import com.kuldeep.aurora.features.chatList.domain.model.isLoggedOut
import com.kuldeep.aurora.navigation.NavAction
import com.kuldeep.aurora.navigation.NavDestination
@Composable
fun ChatListScreen(
    viewModel: ChatListViewModel,
    onNavigation: (NavAction) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Handle opening a chat
    OpenChat(uiState, onNavigation, viewModel::onEvent)

    // Render the appropriate UI based on the login state
    when (uiState.loginState) {
        LoginState.LoggingIn -> Loader()
        LoginState.LoggedIn -> ChatListContent(uiState, viewModel::onEvent)
        LoginState.LoggedOut -> Logout(onNavigation)
    }
}

@Composable
private fun OpenChat(
    uiState: ChatListUiState,
    onNavigation: (NavAction) -> Unit,
    onEvent: (ChatListUiEvent) -> Unit
) {
    LaunchedEffect(uiState.openChat) {
        uiState.openChat?.let { chat ->
            onNavigation(NavAction.NavigateTo(NavDestination.Chat(chat)))
            onEvent(ChatListUiEvent.ClearOpenChat)
        }
    }
}

@Composable
private fun ChatListContent(
    uiState: ChatListUiState,
    onEvent: (ChatListUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val popupMenuItems = listOf(
        PopupMenuItem("Logout") { onEvent(ChatListUiEvent.LogOut) }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            AuroraAppBar(
                title = stringResource(R.string.chats),
                navigationIcon = null,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_chat)
                        )
                    }
                    IconButton(onClick = { onEvent(ChatListUiEvent.PopupMenu(true)) }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.more)
                        )
                    }
                    PopupMenu(
                        expanded = uiState.popUpExpanded,
                        onDismissRequest = { onEvent(ChatListUiEvent.PopupMenu(false)) },
                        items = popupMenuItems
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(ChatListUiEvent.NewChat) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        ChatList(
            modifier = Modifier.padding(innerPadding),
            chats = uiState.chats,
            onEvent = onEvent
        )
    }
}

@Composable
private fun Logout(onNavigation: (NavAction) -> Unit) {
    LaunchedEffect(Unit) {
        onNavigation(NavAction.NavigateToAndClearBackStack(NavDestination.LoginScreen))
    }
}

@Composable
private fun ChatList(
    modifier: Modifier = Modifier,
    chats: List<ChatRoom>,
    onEvent: (ChatListUiEvent) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(chats) { chat ->
            ChatRoomItem(chatRoom = chat) {
                onEvent(ChatListUiEvent.OnChatSelected(chat))
            }
            VerticalDivider()
        }
    }
}