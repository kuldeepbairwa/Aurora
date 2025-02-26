package com.kuldeep.aurora.features.auth.login.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigation: (navDestination: NavAction) -> Unit
) {
    Login()
}


@Composable
fun Login(modifier: Modifier = Modifier) {

    Text("Login Screen")

}