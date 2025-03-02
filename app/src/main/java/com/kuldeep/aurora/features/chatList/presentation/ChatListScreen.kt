package com.kuldeep.aurora.features.chatList.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.twotone.NoteAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuldeep.aurora.core.ui.components.AuroraAppBar
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun ChatListScreen(
    viewModel: ChatListViewModel,
    onNavigation: (NavAction) -> Unit
) {

    val chats = listOf<ChatRoom>()


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
                onClick = onFloatingActionButtonClicked
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.TwoTone.NoteAdd,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(chats) {
                    ChatRoomItem(chatRoom = it){
                        onChatSelected(it)
                    }
                }
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