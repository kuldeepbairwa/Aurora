package com.kuldeep.aurora.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kuldeep.aurora.features.chat.presentation.ChatScreen
import com.kuldeep.aurora.features.chatList.domain.ChatRoom
import com.kuldeep.aurora.features.chatList.presentation.ChatListScreen
import com.kuldeep.aurora.features.login.presentation.LoginScreen
import kotlin.reflect.typeOf

@Composable
fun NavHostAurora(
    navController: NavHostController,
    startDestination: Any
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {

        composable<NavDestination.LoginScreen> {

            LoginScreen(
                viewModel = hiltViewModel()
            ) { navigationAction: NavAction ->
                handleNavigation(navController, navigationAction)
            }

        }
        composable<NavDestination.ChatListScreen> {

            ChatListScreen(
                viewModel = hiltViewModel()
            ) { navigationAction: NavAction ->
                handleNavigation(navController, navigationAction)
            }

        }
        composable<NavDestination.Chat>(
            typeMap = typeMapOf(listOf(typeOf<ChatRoom>()))
        ) { backStackEntry ->

            val chat : NavDestination.Chat = backStackEntry.toRoute()

            ChatScreen(
                chatRoom = chat.chatRoom,
                viewModel = hiltViewModel()
            ) { navigationAction: NavAction ->
                handleNavigation(navController, navigationAction)
            }

        }

    }

}

fun handleNavigation(navController: NavController, navAction: NavAction) {
    when (navAction) {
        NavAction.NavigateUp -> navController.navigateUp()
        is NavAction.NavigateTo -> {
            navController.navigate(navAction.navDestination)
        }

        NavAction.PopBackStack -> navController.popBackStack()
        is NavAction.PopUpTo -> {
            navController.popBackStack(
                navAction.navDestination,
                navAction.inclusive
            )
        }

    }
}