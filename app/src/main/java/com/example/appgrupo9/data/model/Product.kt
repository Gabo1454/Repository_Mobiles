package com.example.appgrupo9.data.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,               // mantener int por la bbdd
    val name: String,
    val descripcion: String,
    val price: Int,             // Int por el bbdd
    val stock: Int,
    val offer: Boolean,
    val image: String?,         // Ruta de imagen
    val categories: List<String>,
    @DrawableRes val imageRes: Int
)