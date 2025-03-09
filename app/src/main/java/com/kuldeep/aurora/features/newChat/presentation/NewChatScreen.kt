package com.kuldeep.aurora.features.newChat.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuldeep.aurora.R
import com.kuldeep.aurora.core.ui.components.AccentButton
import com.kuldeep.aurora.core.ui.components.AuroraAppBar
import com.kuldeep.aurora.core.ui.components.VerticalSpacer
import com.kuldeep.aurora.features.chatList.presentation.ContactItem
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import com.kuldeep.aurora.navigation.NavAction
import com.kuldeep.aurora.navigation.NavDestination

@Composable
fun NewChatScreen(viewModel: NewChatViewModel, onNavigation: (NavAction) -> Unit) {
    val context = LocalContext.current
    val onEvent = viewModel::onEvent
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchTextFieldVisible by remember { mutableStateOf(false) }
    val searchResults = uiState.searchResults
    val contacts = if (searchResults.isNotEmpty()) searchResults else uiState.contacts

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
                onNavigateUp = { onNavigation(NavAction.NavigateUp) },
                actions = {

                    AnimatedVisibility (searchTextFieldVisible) {

                        SearchContact(
                            value = uiState.searchQuery,
                            onValueChange = {
                                onEvent(NewChatUiEvent.SearchQueryChanged(it))
                            },
                            onDismiss = {
                                searchTextFieldVisible = false
                                onEvent(NewChatUiEvent.SearchQueryChanged(""))
                            }
                        )

                    }

                    AnimatedVisibility(!searchTextFieldVisible) {
                        IconButton(onClick = {
                            searchTextFieldVisible = !searchTextFieldVisible }
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = stringResource(R.string.search_chat)
                            )
                        }
                    }
                }
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

            if (uiState.contactPermissionGranted && contacts.isEmpty()) {
                NoContacts()
            } else {
                NewChatContent(contacts = contacts) {


                    onNavigation(
                        NavAction.NavigateToPopUpInclusive(
                            NavDestination.Chat(it),
                            NavDestination.NewChat
                        )
                    )

                }
            }
        }
    }
}


@Composable
fun SearchContact(
    value: String,
    onValueChange: (String) -> Unit,
    onDismiss:() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            singleLine = true
        )

        IconButton(onClick = { onDismiss() }) {
            Icon(
                Icons.Default.Close,
                contentDescription = stringResource(R.string.close_search)
            )
        }

    }
}


@Composable
fun NewChatContent(contacts: List<Contact>, onClick: (Contact) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        LazyColumn {
            items(contacts) { contact ->
                ContactItem(contact = contact) {
                    onClick(contact)
                }
            }
        }

    }
}

@Composable
fun NoContacts(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Text(text = "Add contacts to start a new chat", modifier = Modifier) }
}


private fun checkReadContactsPermission(context: Context) = run {
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED
}

