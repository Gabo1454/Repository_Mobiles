package com.example.appgrupo9.ui.screens.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.ui.utils.obtenerWindowsSizeClass
import com.example.appgrupo9.viewmodel.HomeViewModel
import com.example.appgrupo9.viewmodel.HomeViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userPreferences: UserPreferences,
    onExplorar: () -> Unit,
    onPerfil: () -> Unit,
    onLogin: () -> Unit
) {
    val context = LocalContext.current
    val windowSizeClass = obtenerWindowsSizeClass()

    // ViewModel con Factory
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(userPreferences)
    )

    val permisoConcedido by homeViewModel.permisoConcedido.collectAsState()
    val ubicacion by homeViewModel.ubicacion.collectAsState()
    val isLoggedIn by homeViewModel.isLoggedIn.collectAsState()

    // Launcher de permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        homeViewModel.actualizarPermisoConcedido(granted)
        if (granted) {
            homeViewModel.obtenerUbicacion(context) {
                homeViewModel.setUbicacion(it)
            }
        }
    }

    // Solicitar permiso al iniciar
    LaunchedEffect(Unit) {
        val estado = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (estado == PackageManager.PERMISSION_GRANTED) {
            homeViewModel.actualizarPermisoConcedido(true)
            homeViewModel.obtenerUbicacion(context) {
                homeViewModel.setUbicacion(it)
            }
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    if (!permisoConcedido) {
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
        WindowWidthSizeClass.Medium -> {
            HomeScreenWithDrawer(
                ubicacion = ubicacion,
                onExplorar = onExplorar,
                onPerfil = onPerfil,
                onLogin = onLogin,
                onLogout = { homeViewModel.logout() },
                isLoggedIn = isLoggedIn
            )
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenExpanded(
                ubicacion = ubicacion,
                onExplorar = onExplorar,
                onPerfil = onPerfil
            )
        }
    }
}

@Composable
fun HomeScreenWithDrawer(
    ubicacion: Any?, // Cambia por Location si lo tienes importado
    onExplorar: () -> Unit,
    onPerfil: () -> Unit,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    isLoggedIn: Boolean
) {
    SideMenu(
        isLoggedIn = isLoggedIn,
        onLoginClick = onLogin,
        onLogoutClick = onLogout,
        onPerfilClick = onPerfil
    ) { openDrawer ->
        Column(modifier = Modifier.fillMaxSize()) {
            // Botón para abrir menú lateral
            Text(
                text = "🍔 Abrir menú",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { openDrawer() }
            )

            // Contenido principal siempre visible
            Text(
                text = "🌟 Bienvenido a la app!",
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Opciones rápidas
            Button(onClick = onExplorar, modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("Explorar")
            }

            if (isLoggedIn) {
                Button(onClick = onPerfil, modifier = Modifier.padding(16.dp)) {
                    Text("Perfil")
                }
            }
        }
    }
}

@Composable
fun SideMenu(
    isLoggedIn: Boolean,
    onLoginClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onPerfilClick: () -> Unit,
    content: @Composable ((openDrawer: () -> Unit) -> Unit)
) {
    content {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Menú lateral", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Siempre visible
            Button(onClick = { /* Explorar */ }) { Text("Explorar") }

            // Opciones de perfil si hay sesión
            if (isLoggedIn) {
                Button(onClick = onPerfilClick) { Text("Perfil") }
                Button(onClick = onLogoutClick) { Text("Cerrar sesión") }
            } else {
                Button(onClick = onLoginClick) { Text("Iniciar sesión") }
            }
        }
    }
}
