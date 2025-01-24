package com.example.numbersapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.models.TypeFact
import com.example.numbersapp.data.repository.DatabaseRepository
import com.example.numbersapp.data.repository.FactRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FactRepository = FactRepository(),
    private val databaseRepository: DatabaseRepository = DatabaseRepository()
) : ViewModel() {

    private val _facts = mutableStateListOf<Fact?>()
    val facts: List<Fact?> get() = _facts

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
            nextFact?.let {
                it.isSaved = databaseRepository.getAllFacts().first().any { savedFact ->
                    savedFact.text == it.text
                }
                _facts.add(it)
            }
        }
    }

    fun updateFactSavedStatus(fact: Fact) {
        if (fact.isSaved) deleteFact(fact)
        else saveFact(fact)
    }

    private fun saveFact(fact: Fact) {
        viewModelScope.launch {
            databaseRepository.saveFact(fact)
            val updatedFact = fact.copy(isSaved = true)
            val index = _facts.indexOf(fact)
            if (index != -1) {
                _facts[index] = updatedFact
            }
        }
    }

    private fun deleteFact(fact: Fact) {
        viewModelScope.launch {
            databaseRepository.deleteFact(fact)
            val updatedFact = fact.copy(isSaved = false)
            val index = _facts.indexOf(fact)
            if (index != -1) {
                _facts[index] = updatedFact
            }
        }
    }
}
