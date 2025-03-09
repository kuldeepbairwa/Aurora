package com.kuldeep.aurora.features.newChat.presentation

import com.kuldeep.aurora.features.newChat.domain.model.Contact

data class NewChatUiState(
    val contactPermissionGranted: Boolean = false,
    val requestContactPermission: Boolean = false,
    val contacts: List<Contact> = emptyList(),
    val onContactSelected: Contact? = null,
    val navigateUp: Boolean = false
)
