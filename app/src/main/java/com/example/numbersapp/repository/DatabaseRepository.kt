package com.example.numbersapp.repository

import com.example.numbersapp.App
import com.example.numbersapp.database.FactDAO
import com.example.numbersapp.database.LocalDB
import com.example.numbersapp.models.Fact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DatabaseRepository(
    private val factDAO: FactDAO = LocalDB.getBataBase(App.context).dao(),
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {

    fun getAllFacts(): Flow<List<Fact>> = factDAO.getAllFacts()

    fun getFactFromText(value: String): Flow<Fact> = factDAO.getFactFromText(value)

    fun saveFact(fact: Fact) {
        coroutineScope.launch {
            factDAO.saveFact(fact)
        }
    }

    fun deleteFact(fact: Fact) {
        coroutineScope.launch {
            factDAO.deleteFact(fact)
        }
    }

    fun clearFacts() {
        coroutineScope.launch {
            factDAO.clearFacts()
        }
    }
}