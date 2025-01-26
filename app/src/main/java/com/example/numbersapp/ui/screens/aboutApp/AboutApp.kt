package com.example.numbersapp.ui.screens.aboutApp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.numbersapp.R
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.captionStyle
import com.example.numbersapp.ui.theme.titleStyle

@Composable
fun AboutApp(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {

        AboutAppTitle {
            navController.popBackStack()
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.about_app_description_one),
                style = captionStyle(),
                modifier = Modifier.padding(bottom = 5.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.about_app_description_two),
                style = captionStyle(),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun AboutAppTitle(clickBack: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .clickable { clickBack() }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = CustomTheme.colors.content,
            modifier = Modifier
                .padding(end = 6.dp)
                .size(32.dp),
            contentDescription = "Back"
        )

        Text(
            text = stringResource(R.string.about),
            style = titleStyle(),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}