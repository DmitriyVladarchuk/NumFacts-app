package com.example.numbersapp.domain.repository

import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.models.TypeFact

interface FactRepository {

    suspend fun getRandomFact(typeFact: TypeFact) : Fact?

    suspend fun getUserFact(number: Int, typeFact: TypeFact): Fact?

    suspend fun getUserFact(month: Int, year: Int, typeFact: TypeFact) : Fact?

}