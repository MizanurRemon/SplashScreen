package com.example.splashscreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.splashscreen.ui.theme.HOME_BG
import com.example.splashscreen.ui.theme.PROFILE_BG

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PROFILE_BG)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "PROFILE",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp,
                    fontSize = 16.sp
                )
            )
        }
    }
}


@Composable
@Preview
fun previewProfileScreen() {
    ProfileScreen()
}