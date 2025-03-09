package com.kuldeep.aurora.features.newChat.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuldeep.aurora.core.ui.components.AccentButton
import com.kuldeep.aurora.core.ui.components.AuroraAppBar
import com.kuldeep.aurora.core.ui.components.VerticalSpacer
import com.kuldeep.aurora.features.chatList.presentation.ContactItem
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun NewChatScreen(viewModel: NewChatViewModel, onNavigation: (NavAction) -> Unit) {
    val context = LocalContext.current
    val onEvent = viewModel::onEvent
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            onEvent(NewChatUiEvent.ContactPermissionResult(granted))
        }

    LaunchedEffect(Unit) {
        if (checkReadContactsPermission(context)) {
            onEvent(NewChatUiEvent.ContactPermissionResult(true))
            onEvent(NewChatUiEvent.GetContacts)
        }
    }


    LaunchedEffect(uiState.requestContactPermission) {
        if (uiState.requestContactPermission) {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            onEvent(NewChatUiEvent.RequestContactPermission(false)) // Reset flag
        }
    }


    Scaffold(
        topBar = {
            AuroraAppBar(
                title = "New Chat",
                onNavigateUp = { onNavigation(NavAction.NavigateUp) }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AnimatedVisibility(!uiState.contactPermissionGranted) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Please grant permission to access contacts")
                    VerticalSpacer(12.dp)
                    AccentButton(
                        text = "Grant Permission",
                        onClick = {
                            onEvent(NewChatUiEvent.RequestContactPermission(true))
                        }
                    )
                }
            }
            NewChatContent(contacts = uiState.contacts){



            }
        }
    }
}


@Composable
fun NewChatContent(contacts: List<Contact>,onClick: (Contact) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        if (contacts.isEmpty()) {
            NoContacts()
        }

        ContactsList(contacts = contacts, onClick = onClick)

    }
}

@Composable
fun ContactsList(contacts: List<Contact>,onClick: (Contact) -> Unit) {

    LazyColumn {
        items(contacts) {
            ContactItem(it){
                onClick(it)
            }
        }
    }

}

@Composable
fun NoContacts(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { Text(text = "Add contacts to start a new chat", modifier = Modifier) }
}


private fun checkReadContactsPermission(context: Context) = run {
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED
}

