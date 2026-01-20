package com.example.appgrupo9.data.dto

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String
)