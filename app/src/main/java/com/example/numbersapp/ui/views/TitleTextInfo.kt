package com.example.numbersapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.numbersapp.ui.theme.titleStyle

@Composable
fun TitleTextInfo(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = titleStyle(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
