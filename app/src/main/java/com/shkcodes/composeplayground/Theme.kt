package com.shkcodes.composeplayground

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp


private val colorPalette = darkColors(
    primary = Color(0xFF0DE791),
    primaryVariant = Color(0xFF0AAF6E),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Colors.accent,
    surface = Color(0xFF212121),
    onSecondary = Color(0xFF0DE791),
    onSurface = Color(0xFF0DE791),
    onBackground = Colors.dark,
    error = Color(0xFFFF0000),
    onError = Color(0xFFFFFFFF)
)

object Colors {
    val sidebar = Color(0xFF2B2B2B)
    val accent = Color(0xFFFFC107)
    val dark = Color(0xFF191919)
}

object TextStyles {
    val default = TextStyle(
        color = Color.White,
        fontFamily = fontFamily(font(R.font.champagne))
    )

    val sidebar = default.copy(
        fontSize = 20.sp,
        fontFamily = fontFamily(font(R.font.champagne_bold))
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
