package com.example.numbersapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.models.Fact
import com.example.numbersapp.models.TypeFact
import com.example.numbersapp.repository.FactRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FactRepository = FactRepository()) : ViewModel() {

    private val _facts = mutableStateListOf<Fact?>()
    val facts get() = _facts

    private var _currentTypeFact by mutableStateOf(TypeFact.MATH)
    val currentTypeFact get() = _currentTypeFact


    init {
        loadNextFact()
    }

    fun changeTypeFact(typeFact: TypeFact) {
        if (typeFact != _currentTypeFact) {
            _facts.clear()
            _currentTypeFact = typeFact
            loadNextFact()
        }
    }

    fun loadNextFact() {
        viewModelScope.launch {
            val nextFact = repository.getRandomFact(_currentTypeFact)
            _facts.add(nextFact)
        }
    }

}
