package com.example.appgrupo9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.navigation.AppNavGraph
import com.example.appgrupo9.navigation.NavigationEvent
import com.example.appgrupo9.navigation.Routes
import com.example.appgrupo9.ui.components.layout.SideMenu
import com.example.appgrupo9.ui.theme.GaymerTheme
import com.example.appgrupo9.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val userPreferences by lazy { UserPreferences(applicationContext) }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GaymerTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = viewModel()
                val scope = rememberCoroutineScope()

                // 1. Estado de sesión y comportamiento de scroll e-commerce
                val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initial = false)
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

                // 2. Manejo centralizado de navegación
                LaunchedEffect(Unit) {
                    mainViewModel.navigationEvents.collect { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route) {
                                    event.popUpToRoute?.let { popRoute ->
                                        popUpTo(popRoute) { inclusive = event.inclusive }
                                    }
                                    launchSingleTop = event.singleTop
                                }
                            }
                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                // 3. Estructura Global: SideMenu -> Scaffold -> NavGraph
                SideMenu(
                    isLoggedIn = isLoggedIn,
                    onLoginClick = { navController.navigate(Routes.Login.route) },
                    onRegisterClick = { navController.navigate(Routes.Register.route) },
                    onPerfilClick = { navController.navigate(Routes.Perfil.route) },
                    onLogoutClick = {
                        scope.launch {
                            userPreferences.logout()
                            navController.navigate(Routes.Home.route) { popUpTo(0) }
                        }
                    }
                ) { openDrawer ->
                    Scaffold(
                        // Conecta el scroll del contenido con la barra superior
                        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            TopAppBar(
                                scrollBehavior = scrollBehavior,
                                navigationIcon = {
                                    IconButton(onClick = openDrawer) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menú",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                },
                                title = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(id = R.drawable.logo_level_up),
                                            contentDescription = "Logo",
                                            modifier = Modifier.size(35.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "LEVEL-UP GAMER",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = { /* TODO: Pantalla Buscar */ }) {
                                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                                    }
                                    IconButton(onClick = { /* TODO: Pantalla Carrito */ }) {
                                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                                    }
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            )
                        }
                    ) { innerPadding ->
                        // 4. El NavGraph hereda el padding de la barra para no quedar tapado
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
        }
    }
}