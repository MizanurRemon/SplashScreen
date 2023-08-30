package com.example.splashscreen.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splashscreen.ui.theme.HOME_BG
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = HOME_BG)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "HOME",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp,
                    fontSize = 16.sp
                )
            )

            SignWave()
        }

        //myCanvas()

       // SignWave()
    }
}

@Composable
fun myCanvas() {
    val configuration = LocalConfiguration.current
    Canvas(
        modifier = Modifier
            .size(configuration.screenHeightDp.dp)
            .padding(top = 100.dp),

        ) {
        drawRect(color = Color.Black, size = size)
    }
}

@Composable
fun SignWave() {
    Canvas(modifier = Modifier.fillMaxWidth(), onDraw = {
        drawSignWave(this, size)

     //   drawRect(color = Color.Black, size = size)
    })
}

fun drawSignWave(drawScope: DrawScope, size: Size) {
    val width = size.width
    val height = size.height
    val amplitude = 150
    val frequency = .005f
    val xOffset = 0f
    val yOffset = height / 2f
    val path = Path()
    val yourCurveHeight = 500

    val paint = Paint()
    paint.color = Color.Black
    paint.style = PaintingStyle.Fill

//    for (x in 0..width.toInt()) {
//        val y = (amplitude * sin((frequency * (x + xOffset)).toDouble())).toFloat() + yOffset
//        if (x == 0) {
//            path.moveTo(x.toFloat(), y)
//        } else {
//            path.lineTo(x.toFloat(), y)
//        }
//    }

    path.moveTo(0f, height)              // Move to bottom-left corner
    path.lineTo(0f, height - yourCurveHeight)  // Draw line to start of curve
    path.quadraticBezierTo(width / 2, 0f, width, height - yourCurveHeight) // Define the curve
    path.lineTo(width, height)           // Draw line to bottom-right corner
    path.close()                         // Close the shape

    drawScope.drawPath(
        path,
        color = Color.Red,
        style = Stroke(width = 5f)
    )

}


@Preview
@Composable
fun PreviewSignWave() {
    SignWave()
}

@Composable
@Preview
fun previewMyCanvas() {
    myCanvas()
}

@Composable
@Preview
fun previewDashboardScreen() {
    HomeScreen()
}