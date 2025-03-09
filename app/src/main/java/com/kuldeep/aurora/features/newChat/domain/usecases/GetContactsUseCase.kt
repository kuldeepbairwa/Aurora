package com.kuldeep.aurora.features.newChat.domain.usecases

import com.kuldeep.aurora.features.newChat.domain.model.Contact
import com.kuldeep.aurora.features.newChat.domain.repository.ContactsRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository
) {
    suspend operator fun invoke() = contactsRepository.fetchContacts()
}