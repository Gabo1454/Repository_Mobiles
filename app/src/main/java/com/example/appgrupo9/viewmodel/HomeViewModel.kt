package com.example.appgrupo9.viewmodel

import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.datastore.UserPreferences
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    // --- Ubicación ---
    private val _ubicacion = MutableStateFlow<Location?>(null)
    val ubicacion: StateFlow<Location?> = _ubicacion

    // --- Permiso de ubicación ---
    private val _permisoConcedido = MutableStateFlow(false)
    val permisoConcedido: StateFlow<Boolean> = _permisoConcedido

    // --- Estado de sesión ---
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        // Lee persistencia de login
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { logged ->
                _isLoggedIn.value = logged
            }
        }
    }

    // --- Métodos ---
    fun actualizarPermisoConcedido(valor: Boolean) {
        _permisoConcedido.value = valor
    }

    fun setUbicacion(location: Location?) {
        _ubicacion.value = location
    }

    fun obtenerUbicacion(context: Context, onUbicacionObtenida: (Location?) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    onUbicacionObtenida(location)
                }
        } catch (e: SecurityException) {
            onUbicacionObtenida(null)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
        }
    }
}
