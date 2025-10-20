package com.example.appgrupo9.ui.components.global

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.VideogameAsset
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
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Abrir menú"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenu(
    mainContent: @Composable (openDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Estado para alternar menú Mi Cuenta
    var cuentaActiva by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp),
                drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // --- Logo arriba ---
                    Image(
                        painter = painterResource(id = R.drawable.logo_level_up),
                        contentDescription = "Logo LEVEL-UP",
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (!cuentaActiva) {
                        // --- Menú principal ---
                        DrawerItemWithIcon(text = "Juegos", icon = { Icon(Icons.Default.VideogameAsset, contentDescription = null) }) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon(text = "Consolas", icon = { Icon(Icons.Default.Gamepad, contentDescription = null) }) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon(text = "Accesorios", icon = { Icon(Icons.Default.Headset, contentDescription = null) }) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon(text = "Figuras", icon = { Icon(Icons.Default.EmojiEvents, contentDescription = null) }) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon(text = "Nuestra tienda", icon = { Icon(Icons.Default.Store, contentDescription = null) }) { /* Acción */ }
                    }

                    //Seccion de inicio de sesion
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DrawerItemWithIcon(
                            text = "Iniciar sesión",
                            icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) } // icono de login
                        ) { /* Acción iniciar sesión */ }


                        DrawerItemWithIcon(
                            text = "Crear cuenta",
                            icon = { Icon(Icons.Default.PersonAdd, contentDescription = null) } // icono de agregar usuario
                        ) { /* Acción crear cuenta */ }
                    }
                }
            }
        }
    ) {
        mainContent {
            // Lógica de abrir/cerrar drawer
            scope.launch {
                if (drawerState.isClosed) drawerState.open() else drawerState.close()
            }
        }
    }
}

// --- Componentes auxiliares reutilizables dentro del Drawer ---
@Composable
fun ColumnScope.DrawerItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    )
}

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
            icon() // Aquí va el ícono
            Text(text, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun ColumnScope.DrawerDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}