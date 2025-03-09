package com.kuldeep.aurora.navigation

sealed interface NavAction {
    data class NavigateTo(val navDestination: NavDestination) : NavAction
    data object NavigateUp : NavAction
    data object PopBackStack : NavAction

    data class NavigateToPopUpInclusive(
        val navDestination: NavDestination,
        val inclusiveDestination:NavDestination
    ) : NavAction
    data class NavigateToAndClearBackStack(val navDestination: NavDestination) : NavAction
}