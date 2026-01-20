package com.example.appgrupo9.ui.screens.home

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.ui.components.layout.HamburgerMenuButton
import com.example.appgrupo9.ui.components.layout.SideMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithDrawer(
    ubicacion: Location?,
    onExplorar: () -> Unit,
    onPerfil: () -> Unit,
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    onLogout: () -> Unit,
    isLoggedIn: Boolean
) {
    SideMenu(
        onLoginClick = onLogin,
        onRegisterClick = onRegister,
        onLogoutClick = onLogout,
        onPerfilClick = onPerfil,
        isLoggedIn = isLoggedIn
    ) { openDrawer ->
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("LEVEL-UP STORE") },
                    navigationIcon = {
                        HamburgerMenuButton(onClick = openDrawer)
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                // Llamamos a la única versión de HomeContent
                HomeContent(
                    ubicacion = ubicacion,
                    onExplorar = onExplorar,
                    onPerfil = onPerfil
                )
            }
        }
    }
}
