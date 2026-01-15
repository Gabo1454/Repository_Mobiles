package com.example.appgrupo9.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Home : Routes("home")
    object Explorar : Routes("explorar")

    object Perfil : Routes("perfil")

    object Descuento : Routes("descuento")
}
