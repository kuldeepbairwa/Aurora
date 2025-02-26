package com.kuldeep.aurora.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kuldeep.aurora.core.ui.theme.AuroraTheme
import com.kuldeep.aurora.features.auth.login.presentation.LoginScreen
import com.kuldeep.aurora.navigation.NavDestination
import com.kuldeep.aurora.navigation.NavHostAurora
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuroraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {

                        val navController = rememberNavController()

                        NavHostAurora(
                            navController = navController,
                            startDestination = NavDestination.LoginScreen
                        )
                    }

                }
            }
        }
    }
}