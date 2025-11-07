package com.hitech.pickit.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val DarkColorScheme = darkColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.White,
    secondary = OrangePrimary,
    onSecondary = Color.White,
    background = DarkBackground,
    surface = DarkBackground,
    onBackground = LightText,
    onSurface = LightText
)
// ...
@Composable
fun MovieSearchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // ...
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    // ...
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}