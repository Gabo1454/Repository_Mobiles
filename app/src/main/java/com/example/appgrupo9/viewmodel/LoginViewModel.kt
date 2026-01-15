package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.datastore.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val fakeEmail = "demo@gmail.com"
    private val fakePassword = "1234"

    // Estado observable de sesión
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        // Lee el estado guardado al iniciar la app
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { logged ->
                _isLoggedIn.value = logged
            }
        }
    }

    fun loginFake(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (email == fakeEmail && password == fakePassword) {
            viewModelScope.launch {
                userPreferences.setLoggedIn(true)
                onSuccess()
            }
        } else {
            onError()
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
        }
    }
}
