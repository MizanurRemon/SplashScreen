package com.example.splashscreen.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.splashscreen.BottomNav.BottomScreen
import com.example.splashscreen.firebase.trackNavigationEvents
import com.example.splashscreen.screen.HomeScreen
import com.example.splashscreen.screen.ProfileScreen
import com.example.splashscreen.screen.SettingsScreen

@Composable
fun BottomNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.HOME.route
    ) {
        composable(route = BottomScreen.HOME.route) {
            HomeScreen()
        }

        composable(
            route = BottomScreen.Profile.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://labtechnico.net/my-zoo-/"
                action = Intent.ACTION_VIEW
            })
        ) {
            ProfileScreen()
        }

        composable(route = BottomScreen.Settings.route) {
            SettingsScreen()
        }

        trackNavigationEvents(navController)
    }
}