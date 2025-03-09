package com.kuldeep.aurora.navigation

import com.kuldeep.aurora.features.newChat.domain.model.Contact
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestination {

    @Serializable
    data object LoginScreen : NavDestination

    @Serializable
    data object ChatListScreen:NavDestination

    @Serializable
    data class Chat(val contact: Contact):NavDestination

    @Serializable
    data object NewChat:NavDestination
}
