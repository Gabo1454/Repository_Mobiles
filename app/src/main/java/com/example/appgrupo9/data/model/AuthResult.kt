package com.example.appgrupo9.data.model

data class AuthResult(
    val success: Boolean,
    val token: String?,
    val message: String?
)
