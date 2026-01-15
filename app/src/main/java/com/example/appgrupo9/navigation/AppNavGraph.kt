package com.example.appgrupo9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.ui.screens.auth.LoginScreen
import com.example.appgrupo9.ui.screens.descuentos.DiscountScreen
import com.example.appgrupo9.ui.screens.explorar.ExploreScreen
import com.example.appgrupo9.ui.screens.home.HomeScreen
import com.example.appgrupo9.ui.screens.profile.ProfileScreen
import com.example.appgrupo9.viewmodel.HomeViewModel
import com.example.appgrupo9.viewmodel.HomeViewModelFactory
import com.example.appgrupo9.viewmodel.LoginViewModel
import com.example.appgrupo9.viewmodel.LoginViewModelFactory

@Composable
fun AppNavGraph(
    navController: NavHostController,
    userPreferences: UserPreferences // <- pasa el UserPreferences desde MainActivity o donde inicialices el NavGraph
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {

        // ---------- LOGIN ----------
        composable(Routes.Login.route) {
            val context = LocalContext.current
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(context)
            )

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // ---------- HOME ----------
        composable(Routes.Home.route) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(userPreferences)
            )

            HomeScreen(
                userPreferences = userPreferences,
                onExplorar = { navController.navigate(Routes.Explorar.route) },
                onPerfil = { navController.navigate(Routes.Perfil.route) },
                onLogin = { navController.navigate(Routes.Login.route) }
            )
        }

        // ---------- PERFIL ----------
        composable(Routes.Perfil.route) {
            ProfileScreen(
                userPreferences = userPreferences,
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() } // <- esto agrega el botón de volver
            )
        }

        // ---------- FUNCIONALIDADES ----------
        composable(Routes.Explorar.route) {
            ExploreScreen()
        }

        composable(Routes.Descuento.route) {
            DiscountScreen()
        }
    }
}
