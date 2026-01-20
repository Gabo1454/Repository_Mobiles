package com.example.appgrupo9.data.remote

import retrofit2.http.GET

interface ExternalApiService {
    @GET("last/USD-CLP") // Ejemplo usando AwesomeAPI (gratis y sin registro)
    suspend fun getExchangeRate(): Map<String, ExchangeDetail>
}

data class ExchangeDetail(val bid: String)