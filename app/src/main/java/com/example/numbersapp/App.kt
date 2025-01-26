package com.example.numbersapp

import android.app.Application
import com.example.numbersapp.data.local.PreferenceDataSource
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var preferenceDataSource: PreferenceDataSource

    override fun onCreate() {
        super.onCreate()

        applyUserTheme()
    }

    private fun applyUserTheme() {
        val isDarkModeEnabled = preferenceDataSource.isDarkModeEnabled()
        preferenceDataSource.applyTheme(isDarkModeEnabled)
    }
}