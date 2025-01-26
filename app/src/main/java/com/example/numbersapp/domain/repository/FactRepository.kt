package com.example.numbersapp.domain.repository

import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.models.TypeFact

interface FactRepository {

    suspend fun getRandomFact(typeFact: TypeFact) : Fact?

}