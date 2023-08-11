package com.example.splashscreen.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashscreen.Route.Screens
import com.example.splashscreen.SplashScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    Scaffold() { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.SPLASH.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.SPLASH.name) {
                SplashScreen()
            }
        }
    }
}