package com.example.appgrupo9.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.R
import com.example.appgrupo9.ui.utils.obtenerWindowsSizeClass

// --- Función adaptativa que decide qué layout mostrar según el tamaño de pantalla ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val windowSizeClass = obtenerWindowsSizeClass()
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompact()
        WindowWidthSizeClass.Medium  -> HomeScreenCompact()
        WindowWidthSizeClass.Expanded -> HomeScreenExpanded()
        else -> HomeScreenCompact()
    }
}

// --- Layout para pantallas pequeñas (Compact) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompact() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mi App Kotlin") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultHomeContent()
        }
    }
}

// --- Layout para pantallas grandes (Expanded) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpanded() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Me app Kotlin") }) }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultHomeContent()
        }
    }
}

// --- Contenido común reutilizable ---
@Composable
fun DefaultHomeContent() {
    Text("¡Bienvenido!", color = MaterialTheme.colorScheme.onPrimary)

    Button(onClick = {}) {
        Text("Presioname")
    }

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo App",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentScale = ContentScale.Fit
    )

    Text("Explora Compose y Material3", color = MaterialTheme.colorScheme.secondary)

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = true, onCheckedChange = {})
        Text("Acepto los términos")
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Modo oscuro")
        Switch(checked = false, onCheckedChange = {})
    }
}

// --- Previews para Android Studio ---

@Preview(name = "Compact", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompact() {
    HomeScreenCompact()
}

@Preview(name = "Medium", widthDp = 600, heightDp = 800)
@Composable
fun PreviewMedium() {
    HomeScreenCompact() // o un layout específico para Medium si lo tienes
}

@Preview(name = "Expanded", widthDp = 840, heightDp = 900)
@Composable
fun PreviewExpanded() {
    HomeScreenExpanded()
}
