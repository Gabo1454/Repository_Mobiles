package com.example.appgrupo9.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.model.Product //
import com.example.appgrupo9.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class InventoryViewModel : ViewModel() {

    // Lista de productos que la UI observará
    var inventoryItems by mutableStateOf<List<Product>>(emptyList())
        private set

    // Estado para mostrar un Spinner de carga
    var isLoading by mutableStateOf(false)
        private set

    // Mensaje de error por si algo falla (CORS, Red, etc)
    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchInventory() // Se ejecuta automáticamente al abrir la pantalla
    }

    fun fetchInventory() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                // Llamada real al backend de Java
                val result = RetrofitClient.apiService.getAllProducts()
                inventoryItems = result
                Log.d("INVENTORY_VM", "Éxito: Se cargaron ${result.size} productos")
            } catch (e: Exception) {
                errorMessage = "Error al conectar: ${e.message}"
                Log.e("INVENTORY_VM", "Error en fetchInventory", e)
            } finally {
                isLoading = false
            }
        }
    }
}