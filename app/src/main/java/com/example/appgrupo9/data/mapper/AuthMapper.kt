package com.example.appgrupo9.data.mapper

import com.example.appgrupo9.data.dto.LoginResponse
import com.example.appgrupo9.data.model.AuthResult

fun LoginResponse.toAuthResult() = AuthResult(
    success = true,
    token = token,
    message = null
)
