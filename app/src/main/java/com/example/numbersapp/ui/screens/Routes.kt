package com.example.numbersapp.ui.screens

sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object Like : Routes("Like")
    object Settings : Routes("Settings")
}