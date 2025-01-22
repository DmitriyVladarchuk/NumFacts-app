package com.example.numbersapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val lightColors = CustomColors(
    container = light_container,
    content = light_content,
    background = light_background,
)

private val darkColors = CustomColors(
    container = dark_container,
    content = dark_content,
    background = dark_background,
)

val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    error("Colors composition error")
}

// Чтобы можно было получать цвета из любой части приложения
object CustomTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColors.current
}

@Composable
fun CustomNumbersAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (!darkTheme) lightColors
    else darkColors

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        content = content
    )
}