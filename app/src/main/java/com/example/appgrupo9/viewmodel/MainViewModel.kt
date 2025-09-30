package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appgrupo9.navigation.NavigationEvent
import com.example.appgrupo9.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    // 'receivesAsFlor()' : Expone el channel como un flow de solo lectura para que la ui pueda observarlo.
    //Esto previene que la ui pueda enviar eventos al Channel directamente.

    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()
    //Funcion que emite el evento de navegacion hacia la ruta deseada.

    fun navigateTo(screen : Screen) {
        CoroutineScope(Dispatchers.Main).launch {
            _navigationEvents.emit(NavigationEvent.NavigateTo(route = screen))
        }
    }

    //Funcion para volver atras
    fun navigateBack() {
        CoroutineScope(Dispatchers.Main).launch {
            _navigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    //Funcion para navegar hacia arriba(padre)
    fun navigateUp() {
        CoroutineScope(Dispatchers.Main).launch {
            _navigationEvents.emit(NavigationEvent.NavigateUp)
        }
    }
}