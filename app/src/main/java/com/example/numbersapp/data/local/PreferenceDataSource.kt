package com.example.numbersapp.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(
    private val appContext: Application
) {

    companion object {
        private const val PREFS_NAME = "app_references"
        private const val KEY_DARK_MODE = "dark_mode"
    }

    private val sharedPreferences: SharedPreferences =
        appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isDarkModeEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_DARK_MODE, isSystemDarkModeEnabled())

    }

    fun toggleDarkMode() {
        val newMode = !isDarkModeEnabled()
        sharedPreferences.edit().putBoolean(KEY_DARK_MODE, newMode).apply()
        applyTheme(newMode)
    }

    fun applyTheme(isDarkMode: Boolean) {
        val mode = if (isDarkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun isSystemDarkModeEnabled(): Boolean {
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }
}


