package com.example.splashscreen.screen

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPermissionScreen(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        }
    }

    val snackBarHostState = remember { SnackbarHostState() }
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//    ) {
//        Text(
//            text = "We need permission to send you notifications.",
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        Button(onClick = {
//            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
//        }) {
//            Text("Grant Notification Permission")
//        }
//
//
//    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackBarHostState, snackbar = {
            Snackbar(action = {
                TextButton(content = {
                    Text(text = "Allow")
                }, onClick = { launcher.launch(Manifest.permission.POST_NOTIFICATIONS) })
            }) {

            }
        })
    }) {

    }

}