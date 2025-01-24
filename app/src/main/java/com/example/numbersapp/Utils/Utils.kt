package com.example.numbersapp.Utils

import com.example.numbersapp.R
import com.example.numbersapp.domain.models.TypeFact

class Utils {
    companion object {
        fun getStringResourceIdForFactType(typeFact: TypeFact): Int = when(typeFact) {
                TypeFact.TRIVIA -> R.string.trivia
                TypeFact.MATH -> R.string.math
                TypeFact.DATE -> R.string.date
                TypeFact.YEAR -> R.string.year
            }

    }
}