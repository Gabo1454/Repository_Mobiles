package com.example.appgrupo9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.ui.screens.descuentos.DiscountScreen
import com.example.appgrupo9.ui.screens.explorar.ExploreScreen
import com.example.appgrupo9.ui.screens.home.HomeScreen
import com.example.appgrupo9.ui.screens.login.LoginScreen
import com.example.appgrupo9.ui.screens.login.RegisterScreen
import com.example.appgrupo9.ui.screens.profile.ProfileScreen
import com.example.appgrupo9.ui.screens.profile.AdminUsersScreen
import com.example.appgrupo9.viewmodel.ExploreViewModel
import com.example.appgrupo9.viewmodel.MainViewModel
import com.example.appgrupo9.viewmodel.LoginViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    userPreferences: UserPreferences,
    mainViewModel: MainViewModel
) {
    val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initial = false)

    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.provideFactory(userPreferences)
    )

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        // ---------- LOGIN ----------
        composable(Routes.Login.route) {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.Register.route)
                }
            )
        }

        // ---------- REGISTRO ----------
        composable(Routes.Register.route) {
            RegisterScreen(
                viewModel = loginViewModel,
                onRegisterSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // ---------- HOME ----------
        composable(Routes.Home.route) {
            HomeScreen(
                userPreferences = userPreferences,
                onExplorar = { navController.navigate(Routes.Explorar.route) },
                onPerfil = {
                    if (isLoggedIn) navController.navigate(Routes.Perfil.route)
                    else navController.navigate(Routes.Login.route)
                },
                onLogin = { navController.navigate(Routes.Login.route) }
            )
        }

        // ---------- EXPLORAR ----------
        composable(Routes.Explorar.route) {
            val exploreViewModel: ExploreViewModel = viewModel()
            ExploreScreen(
                viewModel = exploreViewModel,
                onProductClick = { productId ->
                    navController.navigate("product_detail/$productId")
                }
            )
        }

        // ---------- DETALLE ----------
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("productId") ?: 0L
            // Aquí iría tu ProductDetailScreen(id)
        }

        // ---------- DESCUENTOS ----------
        composable(Routes.Descuento.route) {
            DiscountScreen()
        }

        // ---------- PERFIL (CONECTOR PARA ADMIN) ----------
        composable(Routes.Perfil.route) {
            ProfileScreen(
                userPreferences = userPreferences,
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                // 🔥 CONEXIÓN FINAL: Esta lambda es la que activa el botón en ProfileScreen
                onNavigateToAdmin = {
                    navController.navigate(Routes.AdminUsers.route)
                }
            )
        }

        // ---------- GESTIÓN DE USUARIOS (ADMIN) ----------
        composable(Routes.AdminUsers.route) {
            AdminUsersScreen(
                userPreferences = userPreferences,
                onBack = { navController.popBackStack() }
            )
        }
    }
}