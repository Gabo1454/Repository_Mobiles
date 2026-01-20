package com.example.appgrupo9.navigation

sealed class NavigationEvent {

    /*Evento para navegar a un destino especifico
    * */

    data class NavigateTo(
        val route : String,
        val popUpToRoute: String? = null,
        val inclusive : Boolean = false,
        val singleTop: Boolean = false
    ) : NavigationEvent()

    /*
    * Evento para volver a la pantalla anterior aen la pila de navegacion.
    * */

    object PopBackStack : NavigationEvent()

    /*
    * Evento para navegar hacia arriba en la jerarquia de la aplicacion
    * generalmente es equivalente a [PopBackStack] a menos que se yuse un grafo de navegacion
    * con una jerarquia padre- hijo definida
    * */

    object NavigateUp: NavigationEvent()
}