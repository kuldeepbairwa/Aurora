package com.kuldeep.aurora.navigation

sealed interface NavAction {
    data class NavigateTo(val navDestination: NavDestination) : NavAction
    data object NavigateUp : NavAction
    data object PopBackStack : NavAction
    data class PopUpTo(val navDestination: NavDestination,val inclusive:Boolean = false) : NavAction
}