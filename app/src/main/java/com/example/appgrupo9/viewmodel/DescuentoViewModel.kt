package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.datastore.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DescuentoViewModel(
    private val prefs: UserPreferences
) : ViewModel() {

    val codigo = prefs.codigoDescuento
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            null
        )

    fun aplicarCodigo(codigo: String) {
        viewModelScope.launch {
            prefs.guardarCodigo(codigo)
        }
    }
}