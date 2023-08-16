package com.example.splashscreen.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.splashscreen.Utils.screens

class NavigationViewModel(var route : String) : ViewModel() {

    var selectedRoute = mutableStateOf(screens[0].route)

}