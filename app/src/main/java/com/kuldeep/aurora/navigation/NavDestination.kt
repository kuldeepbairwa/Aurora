package com.kuldeep.aurora.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestination {

    @Serializable
    data object LoginScreen : NavDestination
}
