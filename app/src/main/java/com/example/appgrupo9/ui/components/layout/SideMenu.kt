package com.example.appgrupo9.ui.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HamburgerMenuButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenu(
    isLoggedIn: Boolean,               // 🔥 nuevo
    onLoginClick: () -> Unit,
    onPerfilClick: () -> Unit,         // 🔥 nuevo
    onLogoutClick: () -> Unit,         // 🔥 nuevo
    mainContent: @Composable (openDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp),
                drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

                    // --- Logo ---
                    Image(
                        painter = painterResource(id = R.drawable.logo_level_up),
                        contentDescription = "Logo LEVEL-UP",
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Menú principal ---
                    DrawerItemWithIcon("Juegos", { Icon(Icons.Default.VideogameAsset, null) }) {}
                    DrawerDivider()
                    DrawerItemWithIcon("Consolas", { Icon(Icons.Default.Gamepad, null) }) {}
                    DrawerDivider()
                    DrawerItemWithIcon("Accesorios", { Icon(Icons.Default.Headset, null) }) {}
                    DrawerDivider()
                    DrawerItemWithIcon("Figuras", { Icon(Icons.Default.EmojiEvents, null) }) {}
                    DrawerDivider()
                    DrawerItemWithIcon("Nuestra tienda", { Icon(Icons.Default.Store, null) }) {}

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider()

                    // --- LOGIN / PERFIL ---
                    if (!isLoggedIn) {
                        DrawerItemWithIcon("Iniciar sesión", { Icon(Icons.Default.AccountCircle, null) }) {
                            scope.launch {
                                drawerState.close()
                                onLoginClick()
                            }
                        }
                        DrawerItemWithIcon("Crear cuenta", { Icon(Icons.Default.PersonAdd, null) }) {
                            // pendiente
                        }
                    } else {
                        DrawerItemWithIcon("Perfil", { Icon(Icons.Default.AccountCircle, null) }) {
                            scope.launch {
                                drawerState.close()
                                onPerfilClick()
                            }
                        }
                        DrawerItemWithIcon("Cerrar sesión", { Icon(Icons.Default.ExitToApp, null) }) {
                            scope.launch {
                                drawerState.close()
                                onLogoutClick()
                            }
                        }
                    }
                }
            }
        }
    ) {
        mainContent {
            scope.launch {
                if (drawerState.isClosed) drawerState.open() else drawerState.close()
            }
        }
    }
}

// ---------- COMPONENTES AUXILIARES ----------

@Composable
fun DrawerItemWithIcon(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            icon()
            Text(text, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun DrawerDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
