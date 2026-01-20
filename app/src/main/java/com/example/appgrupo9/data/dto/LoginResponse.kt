package com.example.appgrupo9.data.dto

data class LoginResponse(
    val token: String,
    val id: Long,
    val username: String,
    val fullName: String,
    val roleId: Int
)