package com.example.numbersapp.domain.repository

import com.example.numbersapp.domain.models.Fact
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getAllFacts(): Flow<List<Fact>>

    fun getFactFromText(value: String): Flow<Fact>

    suspend fun isFactSaved(text: String): Boolean

    suspend fun saveFact(fact: Fact)

    suspend fun deleteFact(fact: Fact)

    suspend fun clearFacts()
}