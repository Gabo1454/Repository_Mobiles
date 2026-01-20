package com.example.appgrupo9.data.dto

data class ProductDto(
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val description: String,
    val is_offer: Boolean // Como en la BD
)