package com.example.appgrupo9.data.repository

import android.util.Log
import com.example.appgrupo9.R
import com.example.appgrupo9.data.model.Product
import com.example.appgrupo9.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository {

    // Se obtienen los productos desde la API de Spring Boot
    fun getProducts(): Flow<List<Product>> = flow {
        try {
            // Llamado al servicio de Retrofit
            val response = RetrofitClient.apiService.getAllProducts()

            // Emitimos la lista que viene de PostgreSQL
            emit(response)

            Log.d("ProductRepository", "Productos cargados exitosamente: ${response.size}")
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error al cargar productos de la API", e)
            // Si hay un error (ej. servidor apagado), emitimos una lista vacía
            emit(emptyList())
        }
    }
}