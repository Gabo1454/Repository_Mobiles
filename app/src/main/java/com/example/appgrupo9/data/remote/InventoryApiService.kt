package com.example.appgrupo9.data.remote

import com.example.appgrupo9.data.dto.ProductDto
import retrofit2.http.GET

interface InventoryApiService {
    @GET("products") // Tu endpoint en el backend
    suspend fun getInventory(): List<ProductDto>
}