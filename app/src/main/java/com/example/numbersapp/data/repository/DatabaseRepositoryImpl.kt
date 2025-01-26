package com.example.numbersapp.data.repository

import com.example.numbersapp.data.local.FactDAO
import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val factDAO: FactDAO,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DatabaseRepository{

    override fun getAllFacts(): Flow<List<Fact>> = factDAO.getAllFacts()

    override fun getFactFromText(value: String): Flow<Fact> = factDAO.getFactFromText(value)

    override suspend fun isFactSaved(text: String): Boolean {
        return withContext(dispatcher) {
            try {
                val count = factDAO.getFactCountByText(text)
                count > 0
            } catch (e: Exception) {
                false
            }
        }
    }

    override suspend fun saveFact(fact: Fact) {
        withContext(dispatcher) {
            try {
                factDAO.saveFact(fact)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override suspend fun deleteFact(fact: Fact) {
        withContext(dispatcher) {
            try {
                factDAO.deleteFact(fact)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override suspend fun clearFacts() {
        withContext(dispatcher) {
            try {
                factDAO.clearFacts()
            } catch (e: Exception) {
                throw e
            }
        }
    }
}