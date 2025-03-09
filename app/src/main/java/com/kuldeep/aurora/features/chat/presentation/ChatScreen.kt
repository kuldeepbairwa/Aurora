package com.kuldeep.aurora.features.chat.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun ChatScreen(
    contact:Contact,
    viewModel: ChatViewModel,
    onNavigation: (NavAction) -> Unit
) {

    Text(text = contact.phoneNumber)
}