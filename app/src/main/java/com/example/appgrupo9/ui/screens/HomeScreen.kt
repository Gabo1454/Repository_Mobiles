package com.example.appgrupo9.ui.screens

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.R
import com.example.appgrupo9.ui.components.global.GlobalScaffold
import com.example.appgrupo9.ui.utils.obtenerWindowsSizeClass
import com.example.appgrupo9.viewmodel.HomeViewModel
import androidx.compose.runtime.Composable
import com.example.appgrupo9.ui.components.global.HamburgerMenuButton
import com.example.appgrupo9.ui.components.global.SideMenu

// --- Función adaptativa que decide qué layout mostrar según el tamaño de pantalla ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val windowSizeClass = obtenerWindowsSizeClass()
    val permisoConcedido = viewModel.permisoConcedido.collectAsState(initial = false)
    val ubicacion = viewModel.ubicacion.collectAsState(initial = null)

    // Si no hay permiso aún, mostrar mensaje
    if (!permisoConcedido.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("📍 Solicitando permiso de ubicación...")
        }
        return
    }

    //Elegir layout segun tamaño de pantalla
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompact(ubicacion.value)
        WindowWidthSizeClass.Medium  -> HomeScreenCompact(ubicacion.value)
        WindowWidthSizeClass.Expanded -> HomeScreenExpanded(ubicacion.value)
        else -> HomeScreenCompact(ubicacion.value)
    }


}

// --- Layout para pantallas pequeñas (Compact) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompact(ubicacion: android.location.Location?) {
    GlobalScaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultHomeContent(ubicacion)
        }
    }
    }

// --- Layout para pantallas grandes (Expanded) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpanded(ubicacion: android.location.Location?) {
    GlobalScaffold { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultHomeContent(ubicacion)
        }
    }
}

// --- Previews para Android Studio ---

@Preview(name = "Compact", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompact() {
    HomeScreenCompact(ubicacion = null)
}

@Preview(name = "Medium", widthDp = 600, heightDp = 800)
@Composable
fun PreviewMedium() {
    HomeScreenCompact(ubicacion = null) // o un layout específico para Medium si lo tienes
}

@Preview(name = "Expanded", widthDp = 840, heightDp = 900)
@Composable
fun PreviewExpanded() {
    HomeScreenExpanded(ubicacion = null)
}

@Composable
fun DefaultHomeContent(ubicacion: android.location.Location?) {
    Text("¡Bienvenido!", color = MaterialTheme.colorScheme.onPrimary)

    // Mostrar ubicación si existe
    ubicacion?.let {
        Text("Lat: ${it.latitude}, Lon: ${it.longitude}")
    }

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

@Composable
fun UbicacionInfo(ubicacion: Location?) {
    ubicacion?.let {
        Text("Lat: ${it.latitude}, Lon: ${it.longitude}")
    }
}



@Composable
fun HomeScreenWithDrawer(viewModel: HomeViewModel? = null) {
    val isDrawerOpen = remember { mutableStateOf(false) }

    SideMenu(
        isOpen = isDrawerOpen,
        drawerContent = {
            Text("Sección 1")
            Text("Sección 2")
        },
        mainContent = {
            GlobalScaffold {
                Column(Modifier.fillMaxSize()) {
                    HamburgerMenuButton(isDrawerOpen)
                    // Aquí va el resto del contenido de la página
                }
            }
        }
    )
}