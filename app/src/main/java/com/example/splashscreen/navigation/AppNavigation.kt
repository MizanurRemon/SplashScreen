package com.example.splashscreen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashscreen.Route.Screens
import com.example.splashscreen.screen.HomeScreen
import com.example.splashscreen.screen.MainScreen
import com.example.splashscreen.screen.SplashScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    Scaffold { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.MAIN.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.SPLASH.name) {
                SplashScreen(
                    navigateHome = {
                        navController.navigate(Screens.MAIN.name) {
                            popUpTo(navController.graph.id)
                        }
                    })
            }

            composable(route = Screens.MAIN.name) {
                MainScreen()
            }


        }
    }
}