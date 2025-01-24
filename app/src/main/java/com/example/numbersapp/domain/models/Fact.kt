package com.example.numbersapp.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "facts", indices = [Index(value = ["text"], unique = true)])
data class Fact(
    @PrimaryKey
    val text: String,
    val number: Int? = null,
    val type: TypeFact,
    val date: String? = null,
    val year: String? = null,
    @Ignore
    var isSaved: Boolean = false,
    @Ignore
    val found: Boolean,
) {
    constructor(text: String, number: Int?, type: TypeFact, date: String?, year: String?) : this(text, number, type, date, year, false, false)
}
