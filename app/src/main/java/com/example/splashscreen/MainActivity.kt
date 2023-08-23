package com.example.splashscreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import com.example.splashscreen.navigation.AppNavigation
import com.example.splashscreen.ui.theme.SplashScreenTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            val openNotificationDialog = remember {
                mutableStateOf(true)
            }
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

                        sendTokenToServer(token);

                    })




                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                        Log.d(
                            "permissionxx", "onCreate: ${
                                ActivityCompat.checkSelfPermission(
                                    this,
                                    Manifest.permission.POST_NOTIFICATIONS
                                )
                            }"
                        )

                        if (ActivityCompat.checkSelfPermission(
                                this,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.RequestPermission()
                            ) { granted ->

                                if (granted) {
                                    openNotificationDialog.value = false
                                }
                            }

                            if (openNotificationDialog.value) {
                                AlertDialog(
                                    onDismissRequest = { openNotificationDialog.value = false },
                                    confirmButton = {
                                        TextButton(onClick = {

                                            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)

                                        }) {
                                            Text(text = "Settings")
                                        }
                                    },
                                    title = {
                                        Text(
                                            text = "Enable Notification",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp
                                            )
                                        )
                                    },
                                    text = {
                                        Text(text = "You must allow notification permission to use this feature")
                                    },
                                    properties = DialogProperties(
                                        dismissOnBackPress = false,
                                        dismissOnClickOutside = false
                                    )
                                )
                            }


                            return@Surface
                        }

                    }


                  //  getDeepLinksPermission()

//                    if (ActivityCompat.checkSelfPermission(
//                            this,
//                            Manifest.permission.LAUNCH_MULTI_PANE_SETTINGS_DEEP_LINK
//                        ) != PackageManager.PERMISSION_GRANTED
//                    ){
//                        Log.d("dataxx", "getDeepLinksPermission: 1")
//                    }else{
//                        Log.d("dataxx", "getDeepLinksPermission: 2")
//                    }


                }
            }
        }
    }


    @Composable
    private fun getDeepLinksPermission() {
        val context = applicationContext
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Check the result of the installation attempt
            val resultCode = result.resultCode
            // Handle the result as needed
        }

//        val installIntent = Intent(Intent.ACTION_VIEW).apply {
//            setDataAndType(apkUri, "application/vnd.android.package-archive")
//            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//        }

        // You can also prompt the user to enable installations from unknown sources
        val unknownSourcesIntent = Intent(Settings.ACTION_SETTINGS_EMBED_DEEP_LINK_ACTIVITY).apply {
            data = Uri.parse("package:${context.packageName}")
        }

        // Launch the installation or settings activity based on user's choice
//        val installOrSettingsIntent = Intent.createChooser(
//            installIntent,
//            "Install Package"
//        )

        Button(onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // On Android Oreo and above, check if installation from unknown sources is allowed
                if (!context.packageManager.canRequestPackageInstalls()) {
                    // Launch the settings activity to enable installation from unknown sources
                   launcher.launch(unknownSourcesIntent)

                    Log.d("dataxx", "getDeepLinksPermission: 1")
                } else {
                    // Launch the installation activity
                //    launcher.launch(installOrSettingsIntent)
                    Log.d("dataxx", "getDeepLinksPermission: 2")
                }
            } else {
                // On older versions, launch the installation activity directly
            //    launcher.launch(installOrSettingsIntent)
                Log.d("dataxx", "getDeepLinksPermission: 3")
            }
        }) {
            Text("Install Package")
        }
    }

    private fun sendTokenToServer(token: String?) {

        Log.d("dataxx", "onNewToken: $token")
        val deviceToken = hashMapOf(
            "token" to token,
            "timestamp" to FieldValue.serverTimestamp(),
        )
        // Get user ID from Firebase Auth or your own server
        Firebase.firestore.collection("fcmTokens").document(FieldValue.serverTimestamp().toString())
            .set(deviceToken).addOnSuccessListener {
                Log.d("dataxx", "sendTokenToServer: $it")
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