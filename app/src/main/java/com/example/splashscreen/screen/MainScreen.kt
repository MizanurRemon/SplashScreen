package com.example.splashscreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.splashscreen.BottomNav.BottomScreen
import com.example.splashscreen.navigation.BottomNavigationGraph
import com.example.splashscreen.ui.theme.BOTTOM_BAR_BG
import com.example.splashscreen.ui.theme.PROFILE_BG
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {


    var selectedIndex = remember {
        mutableStateOf(0)
    }

    var navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomBar(navController)
    }) {
        BottomNavigationGraph(navController)

        // Text(text = "MAIn")
    }


}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomScreen.HOME,
        BottomScreen.Profile,
        BottomScreen.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    BottomNavigation(
        backgroundColor = BOTTOM_BAR_BG, elevation = 15.dp, modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))

    ) {
        screens.forEach { screen ->
            BottomNavigationItem(
                label = {
                    Text(
                        text = screen.title.uppercase(Locale.ROOT),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 10.sp
                        ),
                        letterSpacing = 2.sp,
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                onClick = { navController.navigate(screen.route) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = false
            )
        }
    }

}

@Composable
@Preview
fun previewHomeScreen() {
    MainScreen()
}