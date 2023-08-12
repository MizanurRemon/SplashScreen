package com.example.splashscreen.Utils

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import kotlinx.coroutines.delay

@Composable
fun TypeWriterText(texts: String, fontSize: TextUnit, fontWeight: FontWeight, color: Color) {

    var textToDisplay by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = texts) {
        for (textInd in texts.indices) {
            textToDisplay += texts[textInd]
            delay(200)

            Log.d("dataxx", "TypeWriterText: ${textToDisplay.toString()}")
        }
    }


    Text(text = textToDisplay, fontSize = fontSize, fontWeight = fontWeight, color = color)

}