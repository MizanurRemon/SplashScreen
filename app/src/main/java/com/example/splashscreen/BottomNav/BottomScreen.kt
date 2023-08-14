package com.example.splashscreen.BottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreen(
    val route: String,
    val title: String,
    val icon : ImageVector
){
    object HOME : BottomScreen(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home
    )
    object Profile : BottomScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Rounded.Person
    )
    object Settings : BottomScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Rounded.Settings
    )
}
