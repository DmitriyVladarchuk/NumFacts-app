package com.example.numbersapp.ui.screens

sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object Favorite : Routes("Favorite")
    object Settings : Routes("Settings")
}