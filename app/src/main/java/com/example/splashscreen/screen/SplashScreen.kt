package com.example.splashscreen.screen

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.splashscreen.Utils.TypeWriterText
import com.example.splashscreen.ui.theme.GRADIENT
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navigateHome: () -> Unit) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navigateHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GRADIENT),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val welcome = "WELCOME"

        TypeWriterText(texts = welcome, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)

        Text(
            text = "SPLASH", color = Color.White, fontSize = 12.sp
        )
    }

}

@Composable
@Preview
fun previewSplashScreen() {
    SplashScreen(navigateHome = {})
}