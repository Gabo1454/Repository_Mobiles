package com.example.appgrupo9.ui.utils

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass

/**
 * Función reutilizable para obtener el tamaño de la ventana (WindowSizeClass).
 * Esto permite adaptar el diseño de la app según el ancho de pantalla:
 * Compact, Medium o Expanded.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun obtenerWindowsSizeClass(): WindowSizeClass {
    // Obtenemos la Activity actual desde el Context
    val activity = LocalContext.current as Activity

    // Calculamos el WindowSizeClass (ancho/alto de la ventana)
    return calculateWindowSizeClass(activity)
}
