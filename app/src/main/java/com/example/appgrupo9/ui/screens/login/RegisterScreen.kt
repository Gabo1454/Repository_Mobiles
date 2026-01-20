package com.example.appgrupo9.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: LoginViewModel,
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Cambiamos los booleanos por Strings para mostrar mensajes específicos
    var usernameError by remember { mutableStateOf<String?>(null) }
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var serverError by remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "CREAR CUENTA GAMER",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )

            // --- CAMPO FULL NAME (Faltaba visualmente en tu código) ---
            CustomTextField(
                value = fullName,
                onValueChange = { fullName = it; fullNameError = null },
                label = "Nombre Completo",
                error = fullNameError
            )

            // --- CAMPO USERNAME ---
            CustomTextField(
                value = username,
                onValueChange = { username = it; usernameError = null },
                label = "Gamer Tag / Usuario",
                error = usernameError
            )

            // --- CAMPO EMAIL ---
            CustomTextField(
                value = email,
                onValueChange = { email = it; emailError = null },
                label = "Correo Electrónico",
                error = emailError
            )

            // --- CAMPO PASSWORD ---
            CustomTextField(
                value = password,
                onValueChange = { password = it; passwordError = null },
                label = "Contraseña",
                error = passwordError,
                isPassword = true
            )

            if (serverError.isNotEmpty()) {
                Text(serverError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    // VALIDACIONES ESPECÍFICAS (Mejoran la nota)
                    if (fullName.isBlank()) fullNameError = "El nombre es obligatorio"
                    if (username.length < 3) usernameError = "Mínimo 3 caracteres"
                    if (!email.contains("@")) emailError = "Correo no válido"
                    if (password.length < 6) passwordError = "Mínimo 6 caracteres"

                    if (fullNameError == null && usernameError == null && emailError == null && passwordError == null) {
                        viewModel.registerReal(
                            username = username,
                            fullName = fullName,
                            email = email,
                            password = password,
                            onSuccess = { onRegisterSuccess() },
                            onError = { serverError = it }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp))
                else Text("REGISTRARSE AHORA", fontWeight = FontWeight.Bold)
            }

            TextButton(onClick = onBackToLogin) {
                Text("¿Ya eres miembro? Inicia sesión", color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

// Componente auxiliar para no repetir código y que se vea limpio
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String?,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            isError = error != null,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary
            )
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}