package com.example.basecomposeproject.core.design.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFE53E3E),
    secondary = Color(0xFFF0CB46),
    tertiary = Color(0xFF4A90E2),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF5F5F5),
    surfaceVariant = Color(0xFFEEEEEE),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    onSurfaceVariant = Color(0xFF424242),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFE57373),
    secondary = Color(0xFFFFF176),
    tertiary = Color(0xFF64B5F6),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    surfaceVariant = Color(0xFF212121),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFFEEEEEE),
    onSurface = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFFD0D0D0),
)

@Composable
fun BaseComposeProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
