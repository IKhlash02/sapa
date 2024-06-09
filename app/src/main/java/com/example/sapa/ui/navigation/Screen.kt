package com.example.sapa.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Dictionary: Screen("Dictionary")
    object Profile: Screen("profile")
    object Question: Screen("home/{id}"){
        fun createRoute(id: Int) = "home/$id"
    }
    object  Congratulation: Screen("congratulation")
    object Intro: Screen("intro")
}