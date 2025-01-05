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
import java.time.LocalDate

class HomeViewModel(private val repository: FactRepository = FactRepository()) : ViewModel() {

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
            val randomNumber = (1..1000).random()
            val nextFact = when(_currentTypeFact) {
                TypeFact.MATH -> repository.getMathFact(randomNumber)
                TypeFact.TRIVIA -> repository.getTriviaFact(randomNumber)
                TypeFact.DATE -> repository.getDateFact(
                    (1..12).random(),
                    (1..LocalDate.now().year).random()
                )
                TypeFact.YEAR -> repository.getYearFact(randomNumber)
            }
            _facts.add(nextFact)
        }
    }

}
