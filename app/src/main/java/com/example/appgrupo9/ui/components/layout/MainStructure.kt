package com.example.appgrupo9.ui.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appgrupo9.R
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.navigation.AppNavGraph
import com.example.appgrupo9.navigation.Routes
import com.example.appgrupo9.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainStructure(
    userPreferences: UserPreferences,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scope = rememberCoroutineScope()

    // Obtenemos el estado real de la sesión desde DataStore
    val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initial = false)

    SideMenu(
        isLoggedIn = isLoggedIn, // Ahora conectado al estado real
        onLoginClick = {
            navController.navigate(Routes.Login.route)
        },
        onRegisterClick = {
            navController.navigate(Routes.Register.route)
        },
        onPerfilClick = {
            navController.navigate(Routes.Perfil.route)
        },
        onLogoutClick = {
            scope.launch {
                userPreferences.logout()
                navController.navigate(Routes.Home.route) {
                    popUpTo(0) // Limpia todo el historial al cerrar sesión
                }
            }
        }
    ) { openDrawer ->
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        // --- LOGO CLICABLE PARA VOLVER AL HOME ---
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Routes.Home.route) {
                                        // Evita duplicar la pantalla de Home en el stack
                                        popUpTo(Routes.Home.route) { inclusive = true }
                                    }
                                }
                                .padding(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_level_up),
                                contentDescription = "Ir al inicio",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "LEVEL-UP GAMER",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = openDrawer) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir Menú")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Lógica de búsqueda */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar")
                        }
                        IconButton(onClick = { /* Lógica de carrito */ }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavGraph(
                    navController = navController,
                    userPreferences = userPreferences,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}