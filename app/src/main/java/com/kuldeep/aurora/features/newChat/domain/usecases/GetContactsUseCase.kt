package com.kuldeep.aurora.features.newChat.domain.usecases

import com.kuldeep.aurora.features.newChat.domain.model.Contact
import javax.inject.Inject

class GetContactsUseCase @Inject constructor() {
    operator fun invoke(): List<Contact>  = emptyList()
}