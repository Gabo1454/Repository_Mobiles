package com.example.appgrupo9.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GamerDarkColorScheme = darkColorScheme(
    primary = NeonGreen,
    secondary = ElectricBlue,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.Black,       // Texto negro sobre botones verdes
    onSecondary = Color.White,     // Texto blanco sobre botones azules
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,

    // ESTO ARREGLA TUS TEXTFIELDS:
    onSurfaceVariant = NeonGreen,  // El label (etiqueta) será verde neón
    outline = ElectricBlue,        // El borde del cuadro será azul eléctrico
    error = ErrorRed
)

@Composable
fun GaymerTheme(
    content: @Composable () -> Unit
) {
    // Forzamos el modo oscuro siempre para mantener la estética gamer
    MaterialTheme(
        colorScheme = GamerDarkColorScheme,
        typography = Typography,
        content = content
    )
}