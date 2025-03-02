package com.kuldeep.aurora.navigation

import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestination {

    @Serializable
    data object LoginScreen : NavDestination

    @Serializable
    data object ChatListScreen:NavDestination

    @Serializable
    data class ChatScreen(val chatRoomId:String):NavDestination
}
