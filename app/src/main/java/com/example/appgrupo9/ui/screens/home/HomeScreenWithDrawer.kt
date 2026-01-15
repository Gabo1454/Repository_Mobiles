package com.example.appgrupo9.ui.screens.home

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.appgrupo9.ui.components.layout.GlobalScaffold
import com.example.appgrupo9.ui.components.layout.HamburgerMenuButton
import com.example.appgrupo9.ui.components.layout.SideMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithDrawer(
    ubicacion: Location?,
    onExplorar: () -> Unit,
    onPerfil: () -> Unit,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    isLoggedIn: Boolean
) {
    SideMenu(
        onLoginClick = onLogin,
        onLogoutClick = onLogout,
        onPerfilClick = onPerfil, // ← agregado
        isLoggedIn = isLoggedIn
    ) { openDrawer ->
        GlobalScaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                HamburgerMenuButton(onClick = openDrawer)

                HomeContent(
                    ubicacion = ubicacion,
                    onExplorar = onExplorar,
                    onPerfil = onPerfil
                )
            }
        }
    }
}
