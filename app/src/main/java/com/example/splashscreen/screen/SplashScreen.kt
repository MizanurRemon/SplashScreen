package com.example.splashscreen.screen

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.splashscreen.Route.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateHome: () -> Unit) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navigateHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SPLASH SCREEN",
            style = TextStyle(fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        )
    }

}

@Composable
@Preview
fun previewSplashScreen() {
    SplashScreen(navigateHome = {})
}