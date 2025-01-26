package com.example.numbersapp.domain.repository

interface SettingsRepository {

    fun isDarkModeEnabled(): Boolean

    fun toggleDarkMode()

}