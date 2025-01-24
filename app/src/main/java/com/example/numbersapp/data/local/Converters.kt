package com.example.numbersapp.data.local

import androidx.room.TypeConverter
import com.example.numbersapp.domain.models.TypeFact
import java.util.Locale

class Converters {
    @TypeConverter
    fun fromTypeFact(type: TypeFact): String {
        return type.type
    }

    @TypeConverter
    fun toTypeFact(type: String): TypeFact {
        return TypeFact.valueOf(type.uppercase(Locale.ROOT))
    }
}