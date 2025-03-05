package com.kuldeep.aurora.features.chatList.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    if(!uiState.isUserLoggedIn){
        LaunchedEffect(Unit) {
            onNavigation(NavAction.NavigateTo(NavDestination.LoginScreen))
        }
    }

    ChatList(
        chats = uiState.chats,
        onChatSelected = {
            onNavigation(
                NavAction.NavigateTo(
                    NavDestination.Chat(it)
                )
            )
        },
        onFloatingActionButtonClicked = { viewModel.onEvent(ChatListUiEvent.NewChat) },
        onActionClick = { viewModel.onEvent(ChatListUiEvent.SearchChat(uiState.searchQuery)) },
        onNavigation = { onNavigation(NavAction.NavigateUp) }
    )


}

@Composable
private fun ChatList(
    chats: List<ChatRoom>,
    onChatSelected: (ChatRoom) -> Unit = {},
    onFloatingActionButtonClicked: () -> Unit = {},
    onActionClick: () -> Unit,
    onNavigation: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            AuroraAppBar(
                text = "Chats",
                navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                onNavigate = onNavigation,
                actionIcon = Icons.Default.Search,
                onActionClicked = onActionClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFloatingActionButtonClicked,
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->


            LazyColumn(
                contentPadding = innerPadding,
            ) {
                items(chats) {
                    ChatRoomItem(chatRoom = it){
                        onChatSelected(it)
                    }
                    VerticalDivider()
                }
            }



    }


}

@Composable
@Preview
fun ChatListScreenPreview(modifier: Modifier = Modifier) {

    ChatList(
        chats = listOf(
            ChatRoom("123","1234456567"),
            ChatRoom("123","1234456567"),
            ChatRoom("123","1234456567"),
            ChatRoom("123","1234456567"),
        ),
        onFloatingActionButtonClicked = {},
        onActionClick = {},
        onNavigation = {}
    )
}