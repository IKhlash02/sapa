package com.example.sapa.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Dictionary: Screen("Dictionary")
    data object Profile: Screen("profile")
    data object Question: Screen("home/{id}"){
        fun createRoute(id: Int) = "home/$id"
    }
    data object  Congratulation: Screen("congratulation")
    data object Intro: Screen("intro")
}