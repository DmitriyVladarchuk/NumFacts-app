package com.example.numbersapp

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        private lateinit var instance: App
        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}