package com.example.numbersapp.domain.models

enum class TypeFact(override val type: String) : FactType {
    MATH("math"),
    TRIVIA("trivia"),
    YEAR("year"),
    DATE("date"),
}