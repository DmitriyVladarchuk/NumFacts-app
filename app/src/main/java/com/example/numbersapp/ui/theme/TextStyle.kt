package com.example.numbersapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun titleStyle() = TextStyle(
    color = CustomTheme.colors.content,
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun subtitleStyle() = TextStyle(
    color = CustomTheme.colors.content,
    fontSize = 24.sp,
    fontWeight = FontWeight.Medium,
    textAlign = TextAlign.Center
)

@Composable
fun bodyTextStyle() = TextStyle(
    color = CustomTheme.colors.content,
    fontSize = 18.sp,
    textAlign = TextAlign.Center
)

@Composable
fun captionStyle() = TextStyle(
    color = Color.Gray,
    fontSize = 14.sp,
    //fontWeight = FontWeight.Medium
)


