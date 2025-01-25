package com.example.numbersapp.data.repository

import com.example.numbersapp.App
import com.example.numbersapp.data.local.FactDAO
import com.example.numbersapp.data.local.LocalDB
import com.example.numbersapp.domain.models.Fact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepository(
    private val factDAO: FactDAO = LocalDB.getBataBase(App.context).dao(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getAllFacts(): Flow<List<Fact>> = factDAO.getAllFacts()

    fun getFactFromText(value: String): Flow<Fact> = factDAO.getFactFromText(value)

    suspend fun isFactSaved(text: String): Boolean {
        return withContext(dispatcher) {
            try {
                val count = factDAO.getFactCountByText(text)
                count > 0
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun saveFact(fact: Fact) {
        withContext(dispatcher) {
            try {
                factDAO.saveFact(fact)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun deleteFact(fact: Fact) {
        withContext(dispatcher) {
            try {
                factDAO.deleteFact(fact)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun clearFacts() {
        withContext(dispatcher) {
            try {
                factDAO.clearFacts()
            } catch (e: Exception) {
                throw e
            }
        }
    }
}