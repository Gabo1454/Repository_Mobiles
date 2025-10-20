package com.example.appgrupo9.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// 🎨 Colores personalizados
val GrisFondo = Color(0xFF1E1E1E)       // Gris oscuro elegante
val BlancoTexto = Color(0xFFFFFFFF)     // Blanco puro para contraste

// 🎨 Paleta para modo oscuro
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = GrisFondo,
    surface = GrisFondo,
    onPrimary = BlancoTexto,
    onSecondary = BlancoTexto,
    onTertiary = BlancoTexto,
    onBackground = BlancoTexto,
    onSurface = BlancoTexto
)

// 🎨 Paleta para modo claro (usamos mismo fondo para consistencia)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = GrisFondo,
    surface = GrisFondo,
    onPrimary = BlancoTexto,
    onSecondary = BlancoTexto,
    onTertiary = BlancoTexto,
    onBackground = BlancoTexto,
    onSurface = BlancoTexto
)

@Composable
fun TiendaGamerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
