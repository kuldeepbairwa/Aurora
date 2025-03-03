package com.kuldeep.aurora.features.chat.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun ChatScreen(
    chatRoom:ChatRoom,
    viewModel: ChatViewModel,
    onNavigation: (NavAction) -> Unit
) {

    Text(text = chatRoom.phone)
}