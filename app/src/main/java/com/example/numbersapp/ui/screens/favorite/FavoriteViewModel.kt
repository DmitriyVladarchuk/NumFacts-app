package com.example.numbersapp.ui.screens.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.models.TypeFact
import com.example.numbersapp.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _favoriteFacts: Flow<List<Fact>> = databaseRepository.getAllFacts()

    private var _currentTypeFact by mutableStateOf(TypeFact.MATH)
    val currentTypeFact get() = _currentTypeFact

    private val _currentFavoriteFacts = mutableStateListOf<Fact>()
    val currentFavoriteFacts: List<Fact> get() = _currentFavoriteFacts


    init {
        updateCurrentFavoriteFacts()
    }

    fun changeTypeFact(typeFact: TypeFact) {
        if (typeFact != _currentTypeFact) {
            _currentTypeFact = typeFact
            updateCurrentFavoriteFacts()
        }
    }

    private fun updateCurrentFavoriteFacts() {
        viewModelScope.launch {
            _favoriteFacts.collect { facts ->
                _currentFavoriteFacts.clear()
                _currentFavoriteFacts.addAll(facts.filter { it.type == _currentTypeFact })
            }
        }
    }

    fun saveFact(fact: Fact) {
        viewModelScope.launch {
            databaseRepository.saveFact(fact)
        }
    }

    fun deleteFact(fact: Fact) {
        viewModelScope.launch {
            databaseRepository.deleteFact(fact)
        }
    }

    fun clearFavorites() {
        viewModelScope.launch {
            databaseRepository.clearFacts()
        }
    }
}