package com.shkcodes.composeplayground

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val colorPalette = darkColors(
    primary = Color(0xFF0DE791),
    primaryVariant = Color(0xFF0AAF6E),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFF7A53),
    surface = Color(0xFF212121),
    onSecondary = Color(0xFF0DE791),
    onSurface = Color(0xFF0DE791),
    onBackground = Color(0xFF191919),
    error = Color(0xFFFF0000),
    onError = Color(0xFFFFFFFF)
)

@Composable
fun PlaygroundTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colorPalette,
        content = content,
    )
}
