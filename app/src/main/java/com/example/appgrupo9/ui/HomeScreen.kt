package com.example.appgrupo9.ui  // Define el paquete donde está este archivo (organiza tu código)

// Importaciones necesarias de Jetpack Compose y Material3
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.R

// @OptIn indica que estamos usando una API experimental de Compose Material3.
// En este caso, Scaffold y TopAppBar son parte de esa API experimental.
@OptIn(ExperimentalMaterial3Api::class)

// @Composable significa que esta función **define un componente de UI**.
// Compose puede “verla” y dibujarla en la pantalla. No es solo una función normal.
@Composable
fun HomeScreen() {
    // Scaffold crea una **estructura básica de pantalla**, con TopBar, contenido y espacios reservados
    Scaffold(
        topBar = { // Definimos la barra superior
            TopAppBar(
                title = { Text("Mi App Kotlin") } // Texto que aparece en la barra
            )
        }
    ) { innerPadding -> // innerPadding = espacio que Scaffold deja para la TopBar u otros elementos
        // Column organiza los elementos **verticalmente**
        Column(
            modifier = Modifier
                .padding(innerPadding) // Aplica el padding que dejó Scaffold
                .fillMaxSize() // Ocupa todo el espacio disponible de la pantalla
                .padding(16.dp), // Padding interno adicional
            verticalArrangement = Arrangement.spacedBy(20.dp), // Espacio de 20dp entre cada elemento
            horizontalAlignment = Alignment.CenterHorizontally //Centrado
        ) {
            Text(text = "¡Bienvenido!",
            color = MaterialTheme.colorScheme.onPrimary
            ) // Texto simple en la pantalla

            // Botón que ejecutará una acción cuando se presione
            Button(
                onClick = { /* Acción futura */ } // Por ahora está vacío
            ) {
                Text("Presioname") // Texto dentro del botón
            }

            // Imagen que se muestra en la pantalla
            Image(
                painter = painterResource(id = R.drawable.logo), // Fuente de la imagen (logo de drawable)
                contentDescription = "Logo App", // Descripción para accesibilidad
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible
                    .height(150.dp), // Altura fija de 150dp
                contentScale = ContentScale.Fit // Ajusta la imagen para que no se deforme
            )

            // Texto secundario con otro color del tema
            Text(
                text = "Explora Compose y Material3",
                color = MaterialTheme.colorScheme.secondary
            )

            // --- NUEVOS ELEMENTOS VISUALES ---

            // Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { /* manejar cambio */ }
                )
                Text("Acepto los términos")
            }

            // Switch
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Modo oscuro")
                Switch(
                    checked = false,
                    onCheckedChange = { /* manejar cambio */ }
                )
            }


        }
    }
}

// @Preview permite **ver la UI directamente en Android Studio** sin ejecutar la app en el emulador
@Preview(showBackground = true)
@Composable // También se necesita @Composable porque Preview llama a una función de UI
fun HomeScreenPreview() {
    HomeScreen() // Llama a HomeScreen para mostrarla en la preview
}
