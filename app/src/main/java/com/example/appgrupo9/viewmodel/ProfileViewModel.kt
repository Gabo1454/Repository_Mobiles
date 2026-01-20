package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.datastore.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    private val _username = MutableStateFlow("Cargando...")
    val username: StateFlow<String> = _username

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        // 1. Observar el nombre de usuario (ESTO FALTABA)
        viewModelScope.launch {
            userPreferences.username.collect { name ->
                // Si el nombre no es nulo ni vacío, lo actualizamos
                if (!name.isNullOrEmpty()) {
                    _username.value = name
                }
            }
        }

        // 2. Observar el estado de la sesión
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { logged ->
                _isLoggedIn.value = logged
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.logout()
        }
    }
}

class ProfileViewModelFactory(private val userPreferences: UserPreferences) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}