package com.example.splashscreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.splashscreen.Utils.screens
import com.example.splashscreen.navigation.BottomNavigationGraph
import com.example.splashscreen.ui.theme.BOTTOM_BAR_BG
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            NavigationDrawer()
        },
        topBar = {
            TopAppBar(backgroundColor = BOTTOM_BAR_BG, title = {
                Text(
                    text = "DEMO APP",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "Toggle Drawer",
                        tint = Color.White
                    )
                }
            })
        }, bottomBar = {
            BottomBar(navController)
        }
    ) {
        BottomNavigationGraph(navController)
    }


}

@Composable
fun NavigationDrawer() {
    LazyColumn() {
        items(screens) { item ->
            Row() {
                Text(text = item.title)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {


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
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.id)
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
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