package com.kuldeep.aurora.features.newChat.domain.repository

import com.kuldeep.aurora.features.newChat.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    suspend fun fetchContacts(): List<Contact>

}