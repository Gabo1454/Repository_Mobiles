package com.example.appgrupo9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo9.data.model.Product
import com.example.appgrupo9.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {

    // Instanciamos el repositorio (En el futuro podrías pasarlo por constructor con un Factory)
    private val repository = ProductRepository()

    // Estado interno mutable de la lista de productos
    private val _products = MutableStateFlow<List<Product>>(emptyList())

    // Estado expuesto como solo lectura para la UI (ExploreScreen)
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            // Recolectamos el Flow que viene del repositorio
            repository.getProducts().collect { list ->
                _products.value = list
            }
        }
    }
}