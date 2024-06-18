package com.example.sapa.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Dictionary: Screen("Dictionary")
    object Profile: Screen("profile")
    object Question: Screen("home/{id}/{name}"){
        fun createRoute(id: Int, name: String) = "home/$id/$name"
    }
    object  Congratulation: Screen("congratulation")
    object Intro: Screen("intro")
}