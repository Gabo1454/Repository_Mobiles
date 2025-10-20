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

    fun actualizarPermiso(valor: Boolean, context: Context) {
        _permisoConcedido.value = valor
        if (valor) {
            obtenerUbicacion(context)
        }
    }

    private fun obtenerUbicacion(context: Context) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    _ubicacion.value = location
                }
        } catch (e: SecurityException) {
            _ubicacion.value = null
        }
    }
}
