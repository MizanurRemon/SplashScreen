package com.example.splashscreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.splashscreen.navigation.AppNavigation
import com.example.splashscreen.screen.MainScreen
import com.example.splashscreen.ui.theme.SplashScreenTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()

                    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->


                        if (!task.isSuccessful) {
                            return@OnCompleteListener
                        }
                        val token = task.result

                        Log.d("dataxx", "token::  $token")

                        //    Toast.makeText(this, token, Toast.LENGTH_SHORT).show()

                    })

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                        if (ActivityCompat.checkSelfPermission(
                                this,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {

//                            Snackbar.make(
//                                this,findViewById<View>(android.R.id.content).rootView,
//                                "Please grant Notification permission from App Settings",
//                                Snackbar.LENGTH_LONG
//                            ).show()

//                            lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
//                            requestPermissionLauncher =
//                                registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//                                    if (!it) {
//                                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//
//                                    }
//                                }

                            Toast.makeText(
                                applicationContext,
                                "give permission",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Surface
                        }

                    }


                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashScreenTheme {
        Greeting("Android")
    }
}