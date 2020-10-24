package com.shkcodes.composeplayground

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


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

object Colors {
    val sidebarColor = Color(0xFF2B2B2B)
}

object TextStyles {
    val sidebarTextStyle =
        TextStyle(
            fontFamily = fontFamily(font(R.font.rokkitt_light)), color = Color.White,
            fontSize = 20.sp
        )
}


@Composable
fun PlaygroundTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colorPalette,
        content = content,
    )
}
