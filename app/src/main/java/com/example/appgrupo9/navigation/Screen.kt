package com.example.appgrupo9.navigation
//sirve para manejar la navegación desde ViewModels o controladores
sealed class Screen(val route: String) {
    /*
    * Rutas simples sin argumentos :  Usamos data object
    *
    * Representa la ruta a la pantalla de inicio
    * */

    data object Home :  Screen("home_page")

    data object Profile : Screen("profile_page")

    data object Settings : Screen("settings_page")

    /*
    * Ejemplo de una ruta a una pantalla de detalles que requiere un itemId
    * */
    data class Detail(val itemId: String) : Screen("detail_page/{itemId}"){
        //funcion  para construir la ruta final con el argumento
        //Esto evita errores al crear la ruta string.
        fun buildRoute() : String {
            //Reemplaza el placeholder "{itemId} es la ruta base con el valor real."
            return route.replace("{itemId}", itemId)
        }
    }

    //Si tuviera mas argumentos, se agregarias a la data class y a la cadena de ruta
    //data class UserProfile(val userId: String, val userNmae : String?) :
    //      AppDestinations("profile/{userId}? name={userName}") {----}

}