package com.example.numbersapp.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository = SettingsRepository()
) : ViewModel() {

    private var _isDArkModeEnabled by mutableStateOf(settingsRepository.isDarkModeEnabled())
    val isDArkModeEnabled get() = _isDArkModeEnabled

    fun toggleDarkMode() {
        viewModelScope.launch {
            settingsRepository.toggleDarkMode()
            _isDArkModeEnabled = settingsRepository.isDarkModeEnabled()
        }
    }
}