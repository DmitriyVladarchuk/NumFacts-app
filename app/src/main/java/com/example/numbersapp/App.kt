package com.example.numbersapp

import android.app.Application
import android.content.Context
import com.example.numbersapp.data.local.PreferenceDataSource

class App : Application() {

    companion object {
        private lateinit var instance: App
        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        applyUserTheme()
    }

    private fun applyUserTheme() {
        val preferenceDataSource = PreferenceDataSource(this)
        val isDarkModeEnabled = preferenceDataSource.isDarkModeEnabled()
        preferenceDataSource.applyTheme(isDarkModeEnabled)
    }
}