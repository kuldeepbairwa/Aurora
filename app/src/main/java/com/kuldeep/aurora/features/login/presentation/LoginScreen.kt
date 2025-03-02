package com.kuldeep.aurora.features.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuldeep.aurora.core.ui.components.AccentButton
import com.kuldeep.aurora.core.ui.components.VerticalSpacer
import com.kuldeep.aurora.navigation.NavAction

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigation: (navDestination: NavAction) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Login(uiState = uiState, onEvent = viewModel::onEvent)
}


@Composable
fun Login(
    uiState: LoginUiState,
    onEvent: (event: LoginUiEvent) -> Unit
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "Welcome",
            style = MaterialTheme.typography.headlineLarge,
        )

        VerticalSpacer(60.dp)

        OutlinedTextField(
            value = uiState.phone,
            onValueChange = {
                onEvent(LoginUiEvent.OnPhoneChange(it))
            },
            placeholder = {
                Text("Enter Your Phone Number")
            }
        )



        VerticalSpacer(40.dp)




        AccentButton(
            text = "Login",
            onClick = { onEvent(LoginUiEvent.Login) }
        )


    }


}