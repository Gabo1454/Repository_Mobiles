package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.data.dto.UserResponse
import com.example.appgrupo9.data.dto.UpdateUserRequest
import com.example.appgrupo9.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AdminViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    private val _users = MutableStateFlow<List<UserResponse>>(emptyList())
    val users: StateFlow<List<UserResponse>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        cargarUsuarios()
    }

    fun cargarUsuarios() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = userPreferences.authToken.first()
                if (!token.isNullOrEmpty()) {
                    val lista = RetrofitClient.apiService.getAllUsers("Bearer $token")
                    _users.value = lista
                } else {
                    _errorMessage.value = "No se encontró un token válido"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al conectar con el servidor: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 🔥 MÉTODO PARA ACTUALIZAR (EDITAR)
    fun actualizarUsuario(userId: Long, nuevoNombre: String) {
        viewModelScope.launch {
            try {
                val token = userPreferences.authToken.first() ?: ""
                val response = RetrofitClient.apiService.updateUser(
                    token = "Bearer $token",
                    id = userId,
                    request = UpdateUserRequest(username = nuevoNombre)
                )

                if (response.isSuccessful) {
                    cargarUsuarios() // Refresca la lista automáticamente
                } else {
                    _errorMessage.value = "Error al actualizar: Permisos insuficientes"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }

    fun eliminarUsuario(userId: Long) {
        viewModelScope.launch {
            try {
                val token = userPreferences.authToken.first() ?: ""
                val response = RetrofitClient.apiService.deleteUser("Bearer $token", userId)

                if (response.isSuccessful) {
                    cargarUsuarios()
                } else {
                    _errorMessage.value = "No se pudo eliminar el usuario"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar: ${e.message}"
            }
        }
    }
}

class AdminViewModelFactory(private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AdminViewModel(userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}