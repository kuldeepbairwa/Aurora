package com.kuldeep.aurora.features.chat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuldeep.aurora.R
import com.kuldeep.aurora.core.ui.components.AuroraAppBar
import com.kuldeep.aurora.core.ui.components.HorizontalSpacer
import com.kuldeep.aurora.core.ui.components.PopupMenu
import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.chat.presentation.components.MessageItem
import com.kuldeep.aurora.features.chatList.presentation.ChatListUiEvent
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun ChatScreen(
    contact: Contact,
    viewModel: ChatViewModel,
    onNavigation: (NavAction) -> Unit
) {

    viewModel.contactBundle(contact)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AuroraAppBar(
                title = contact.name,
                navigationIcon = null,
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            ChatContent(
                messages = uiState.messages,
                messageValue = uiState.message,
                onEvent = viewModel::onEvent
                )

        }

    }
}

@Composable
fun ChatContent(
    messages:List<Message>,
    messageValue: String,
    onEvent:(ChatUiEvents)->Unit,
    modifier: Modifier = Modifier) {

    DisposableEffect(Unit) {
        onEvent(ChatUiEvents.ConnectToChatRoom)
        onDispose {
            onEvent(ChatUiEvents.DisconnectFromChatRoom)
        }
    }

    Column(modifier = modifier) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) {
                MessageItem(it)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            HorizontalSpacer(8.dp)

            TextField(
                value = messageValue,
                onValueChange = {
                    onEvent(ChatUiEvents.MessageChange(it))
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            HorizontalSpacer(16.dp)

            IconButton(onClick = {
                onEvent(ChatUiEvents.SendMessageClick)
            }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }

            HorizontalSpacer(8.dp)

        }

    }
}