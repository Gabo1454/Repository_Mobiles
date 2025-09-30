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

// Función adaptativa de ProfileScreen
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ProfileScreenAdaptive() {
    val activity = LocalContext.current as Activity
    val windowSize = calculateWindowSizeClass(activity)

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ProfileScreenCompact()
        WindowWidthSizeClass.Medium -> ProfileScreenMedium()
        WindowWidthSizeClass.Expanded -> ProfileScreenExpanded()
        else -> ProfileScreenCompact()
    }
}

// ---------------------------------------
// Layouts por tamaño
// ---------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenCompact() {
    Scaffold(topBar = { TopAppBar(title = { Text("Perfil") }) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultProfileContent()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenMedium() {
    Scaffold(topBar = { TopAppBar(title = { Text("Perfil") }) }) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultProfileContent()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenExpanded() {
    Scaffold(topBar = { TopAppBar(title = { Text("Perfil") }) }) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultProfileContent()
        }
    }
}

// Contenido compartido de la pantalla
@Composable
fun DefaultProfileContent() {
    Text("Nombre de usuario", style = MaterialTheme.typography.headlineMedium)
    Text("Correo: usuario@ejemplo.com")
    Button(onClick = { /* acción */ }) {
        Text("Editar perfil")
    }
    Button(onClick = { /* acción */ }) {
        Text("Cerrar sesión")
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenAdaptive()
}
