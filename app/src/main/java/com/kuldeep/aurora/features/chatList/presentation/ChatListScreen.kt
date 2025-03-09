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
import com.kuldeep.aurora.core.ui.components.VerticalDivider
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.navigation.NavAction
import com.kuldeep.aurora.navigation.NavDestination

@Composable
fun ChatListScreen(
    viewModel: ChatListViewModel,
    onNavigation: (NavAction) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isUserLoggedIn, uiState.openChat) {
        if (!uiState.isUserLoggedIn) {
            onNavigation(NavAction.NavigateTo(NavDestination.LoginScreen))
        }
        uiState.openChat?.let {
            onNavigation(NavAction.NavigateTo(NavDestination.Chat(it)))
            viewModel.onEvent(ChatListUiEvent.ClearOpenChat)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.more)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(ChatListUiEvent.NewChat) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        ChatList(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            chats = uiState.chats,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun ChatList(
    modifier: Modifier = Modifier,
    chats: List<ChatRoom>,
    onEvent: (ChatListUiEvent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(chats) { chat ->
            ChatRoomItem(chatRoom = chat) {
                onEvent(ChatListUiEvent.OnChatSelected(chat))
            }
            VerticalDivider()
        }
    }
}
