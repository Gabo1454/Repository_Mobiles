package com.example.appgrupo9.ui.components.global

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import com.example.appgrupo9.R
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.vector.ImageVector

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
    navController: NavHostController,
    mainContent: @Composable (openDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
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
                        DrawerItemWithIcon("Juegos", Icons.Default.VideogameAsset) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon("Consolas", Icons.Default.Gamepad) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon("Accesorios", Icons.Default.Headset) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon("Figuras", Icons.Default.EmojiEvents) { /* Acción */ }
                        DrawerDivider()
                        DrawerItemWithIcon("Nuestra tienda", Icons.Default.Store) { /* Acción */ }
                    }

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
                        DrawerItemWithIcon("Iniciar sesión", Icons.Default.AccountCircle) {
                            // Acción iniciar sesión
                        }

                        DrawerItemWithIcon("Crear cuenta", Icons.Default.PersonAdd) {
                            scope.launch {
                                drawerState.close()
                                navController.navigate("registro")
                            }
                        }
                    }
                }
            }
        }
    ) {
        mainContent {
            scope.launch {
                drawerState.open()
            }
        }
    }
}

// --- Componentes auxiliares ---
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
    icon: ImageVector,
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
            Icon(imageVector = icon, contentDescription = null)
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
