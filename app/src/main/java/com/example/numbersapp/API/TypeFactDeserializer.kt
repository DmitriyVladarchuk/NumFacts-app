package com.example.numbersapp.API

import com.example.numbersapp.models.TypeFact
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class TypeFactDeserializer : JsonDeserializer<TypeFact> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TypeFact {
        val type = json?.asString
        return TypeFact.entries.find {
            it.type == type
        } ?: throw JsonParseException("Unknown type: $type")
    }
}