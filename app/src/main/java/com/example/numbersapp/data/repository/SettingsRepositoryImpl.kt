package com.example.numbersapp.data.repository

import com.example.numbersapp.App
import com.example.numbersapp.data.local.PreferenceDataSource
import com.example.numbersapp.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataSource: PreferenceDataSource
) : SettingsRepository {

    override fun isDarkModeEnabled(): Boolean = dataSource.isDarkModeEnabled()

    override fun toggleDarkMode() {
        dataSource.toggleDarkMode()
    }

}