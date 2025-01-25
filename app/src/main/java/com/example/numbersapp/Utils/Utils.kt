package com.example.numbersapp.Utils

import android.content.Context
import android.content.Intent
import android.net.Uri
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

        fun openUrl(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        }

        fun sendEmail(context: Context, title: String, email: String) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822" // Указывает, что это email
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            }
            context.startActivity(Intent.createChooser(intent, title))
        }

    }
}