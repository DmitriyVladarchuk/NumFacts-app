package com.example.numbersapp.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.models.Fact
import com.example.numbersapp.repository.FactRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel(private val repository: FactRepository = FactRepository()) : ViewModel() {

    var triviaFact by  mutableStateOf<Fact?>(null)
        private set

    var dateFact by mutableStateOf<Fact?>(null)
        private set

    var mathFact by mutableStateOf<Fact?>(null)
        private set

    var yearFact by mutableStateOf<Fact?>(null)
        private set

    var loading by mutableStateOf(false)
        private set

    fun getTriviaFact(number: Int) {
        loading = true
        viewModelScope.launch {
            val fact = repository.getTriviaFact(number)
            triviaFact = fact
            loading = false
        }
    }

    fun getDateFact(month: Int, year: Int) {
        loading = true
        viewModelScope.launch {
            val fact = repository.getDateFact(month, year)
            dateFact = fact
            loading = false
        }
    }

    fun getMathFact(number: Int) {
        loading = true
        viewModelScope.launch {
            val fact = repository.getMathFact(number)
            mathFact = fact
            loading = false
        }
    }

    fun getYearFact(year: Int) {
        loading = true
        viewModelScope.launch {
            val fact = repository.getYearFact(year)
            yearFact = fact
            loading = false
        }
    }

    fun getAllFacts(): List<Fact?> {
        return listOf(triviaFact, mathFact, yearFact, dateFact)
    }

    private fun loadAllFacts() {
        loading = true
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val currentMonth = currentDate.monthValue
            val currentYear = currentDate.year
            val randomNumber = (1..1000).random()

            triviaFact = repository.getTriviaFact(randomNumber)
            mathFact = repository.getMathFact(randomNumber)
            yearFact = repository.getYearFact(currentYear)
            dateFact = repository.getDateFact(currentMonth, currentYear)
            loading = false
        }
    }

}
