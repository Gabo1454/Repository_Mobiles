package com.example.appgrupo9.data.dto

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val fullName: String,
    val roleId: Int
)