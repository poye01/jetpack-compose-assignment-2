package com.example.jetpack_compose_assignment_2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = CardBlue,
    onPrimary =DarkBlueGray ,
    surface = CardBlueDarker,
    onSurface = NavyBlue,
    background = LightBlue
)

private val DarkColors = darkColorScheme(
    primary = NavyBlue,
    onPrimary = White,
    surface = NavyBlue,
    onSurface = LightBlue,
    background = DarkBlueGray,
)

@Composable
fun Jetpackcomposeassignment2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}