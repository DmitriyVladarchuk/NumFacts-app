package com.example.numbersapp.data.repository

import com.example.numbersapp.App
import com.example.numbersapp.data.local.PreferenceDataSource

class SettingsRepository(
    private val dataSource: PreferenceDataSource = PreferenceDataSource(App.context)
) {

    fun isDarkModeEnabled(): Boolean = dataSource.isDarkModeEnabled()

    fun toggleDarkMode() {
        dataSource.toggleDarkMode()
    }

}