package com.example.appgrupo9.ui.screens

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appgrupo9.R
import com.example.appgrupo9.ui.components.global.HamburgerMenuButton
import com.example.appgrupo9.ui.components.global.SideMenu
import com.example.appgrupo9.ui.utils.obtenerWindowsSizeClass
import com.example.appgrupo9.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {
    val windowSizeClass = obtenerWindowsSizeClass()
    val permisoConcedido = viewModel.permisoConcedido.collectAsState(initial = false)
    val ubicacion = viewModel.ubicacion.collectAsState(initial = null)

    if (!permisoConcedido.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("📍 Solicitando permiso de ubicación...")
        }
        return
    }

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> HomeScreenWithDrawer(viewModel, navController)
        WindowWidthSizeClass.Expanded -> HomeScreenExpanded(ubicacion.value)
        else -> HomeScreenWithDrawer(viewModel, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompact(ubicacion: Location?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LEVEL-UP GAMER") }
            )
        }
    ) { innerPadding ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpanded(ubicacion: Location?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LEVEL-UP GAMER") }
            )
        }
    ) { innerPadding ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithDrawer(viewModel: HomeViewModel, navController: NavHostController) {
    SideMenu(navController = navController) { openDrawer ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            HamburgerMenuButton(onClick = openDrawer)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("LEVEL-UP GAMER")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DefaultHomeContent(viewModel.ubicacion.collectAsState(initial = null).value)
            }
        }
    }
}

@Composable
fun DefaultHomeContent(ubicacion: Location?) {
    Text("¡Bienvenido!", color = MaterialTheme.colorScheme.onPrimary)

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

@Preview(name = "Compact", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompact() {
    HomeScreenCompact(ubicacion = null)
}

@Preview(name = "Medium", widthDp = 600, heightDp = 800)
@Composable
fun PreviewMedium() {
    HomeScreenCompact(ubicacion = null)
}

@Preview(name = "Expanded", widthDp = 840, heightDp = 900)
@Composable
fun PreviewExpanded() {
    HomeScreenExpanded(ubicacion = null)
}
