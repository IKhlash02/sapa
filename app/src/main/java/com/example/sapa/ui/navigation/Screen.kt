package com.example.sapa.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Dictionary: Screen("Dictionary")
    object Profile: Screen("profile")
}