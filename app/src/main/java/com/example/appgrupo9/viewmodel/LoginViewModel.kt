package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.data.dto.LoginRequest
import com.example.appgrupo9.data.dto.RegisterRequest
import com.example.appgrupo9.data.remote.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { logged ->
                _isLoggedIn.value = logged
            }
        }
    }

    fun registerReal(
        username: String,
        email: String,
        password: String,
        fullName: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = RegisterRequest(
                    username = username,
                    email = email,
                    password = password,
                    fullName = fullName
                )
                RetrofitClient.apiService.register(request)

                delay(1000)

                try {
                    val loginRequest = LoginRequest(username, password)
                    val loginResponse = RetrofitClient.apiService.login(loginRequest)

                    // 🔥 GUARDAMOS DATOS TRAS REGISTRO
                    userPreferences.saveToken(loginResponse.token)
                    userPreferences.saveUsername(username)

                    // CORRECCIÓN: Verifica que loginResponse.roleId sea de tipo Int
                    val esAdmin = loginResponse.roleId == 1
                    userPreferences.saveAdminStatus(esAdmin)

                    userPreferences.setLoggedIn(true)

                    _isLoading.value = false
                    onSuccess()
                } catch (loginEx: Exception) {
                    _isLoading.value = false
                    onError("¡Gamer Tag creado! Inicia sesión manualmente.")
                }

            } catch (e: Exception) {
                _isLoading.value = false
                onError("No se pudo crear la cuenta.")
            }
        }
    }

    fun loginReal(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = LoginRequest(username, password)
                val response = RetrofitClient.apiService.login(request)

                // 🔥 PASO CRUCIAL
                userPreferences.saveToken(response.token)
                userPreferences.saveUsername(username)

                // Si el error persiste aquí, asegúrate de que 'roleId' en LoginResponse sea 'Int'
                val esAdmin = response.roleId == 1
                userPreferences.saveAdminStatus(esAdmin)

                userPreferences.setLoggedIn(true)

                _isLoading.value = false
                onSuccess()
            } catch (e: Exception) {
                _isLoading.value = false
                onError("Credenciales inválidas o servidor no disponible")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.logout()
        }
    }

    companion object {
        fun provideFactory(userPreferences: UserPreferences): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LoginViewModel(userPreferences) as T
                }
            }
    }
}