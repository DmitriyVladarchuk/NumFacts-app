package com.example.numbersapp.domain.models

enum class FactSource(override val type: String) : FactType {
    RANDOM("random"),
    USER("user"),
}