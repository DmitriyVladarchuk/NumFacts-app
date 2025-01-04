package com.example.numbersapp.models

data class Fact(
    val text: String,
    val found: Boolean,
    val number: Int?,
    val type: TypeFact,
    val date: String?,
)
