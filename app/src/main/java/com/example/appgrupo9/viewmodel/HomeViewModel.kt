package com.example.appgrupo9.viewmodel

import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _ubicacion = MutableStateFlow<Location?>(null)
    val ubicacion: StateFlow<Location?> = _ubicacion

    private val _permisoConcedido = MutableStateFlow(false)
    val permisoConcedido: StateFlow<Boolean> = _permisoConcedido

    //Actualizar eñ estado de permiso
    fun actualizarPermisoConcedido(valor: Boolean) {
        _permisoConcedido.value = valor
    }
    //Guardar ubicacion obtenida
    fun setUbicacion(location: Location?) {
        _ubicacion.value = location
    }

    // --- Función para obtener la ubicación ---
    fun obtenerUbicacion(context: Context, onUbicacionObtenida: (Location?) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    onUbicacionObtenida(location)
                }
        } catch (e: SecurityException) {
            onUbicacionObtenida(null)
        }
    }

}