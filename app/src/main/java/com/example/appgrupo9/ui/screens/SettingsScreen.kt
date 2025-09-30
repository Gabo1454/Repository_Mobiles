package com.example.appgrupo9.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.tooling.preview.Preview

// Función adaptativa de SettingsScreen
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SettingsScreenAdaptive() {
    val activity = LocalContext.current as Activity
    val windowSize = calculateWindowSizeClass(activity)

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> SettingsScreenCompact()
        WindowWidthSizeClass.Medium -> SettingsScreenMedium()
        WindowWidthSizeClass.Expanded -> SettingsScreenExpanded()
        else -> SettingsScreenCompact()
    }
}

// ---------------------------------------
// Layouts por tamaño
// ---------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenCompact() {
    Scaffold(topBar = { TopAppBar(title = { Text("Ajustes") }) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            DefaultSettingsContent()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenMedium() {
    Scaffold(topBar = { TopAppBar(title = { Text("Ajustes") }) }) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.Top
        ) {
            DefaultSettingsContent()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenExpanded() {
    Scaffold(topBar = { TopAppBar(title = { Text("Ajustes") }) }) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            DefaultSettingsContent()
        }
    }
}

// Contenido compartido de la pantalla
@Composable
fun DefaultSettingsContent() {
    Text("Notificaciones", style = MaterialTheme.typography.titleMedium)
    Switch(checked = true, onCheckedChange = { /* acción */ })

    Text("Tema oscuro", style = MaterialTheme.typography.titleMedium)
    Switch(checked = false, onCheckedChange = { /* acción */ })

    Button(onClick = { /* acción */ }) {
        Text("Guardar cambios")
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreenAdaptive()
}
