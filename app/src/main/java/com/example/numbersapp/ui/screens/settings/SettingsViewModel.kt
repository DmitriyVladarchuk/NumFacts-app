package com.example.numbersapp.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
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