package com.example.appgrupo9.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


@Composable
fun HomeContent(
    ubicacion: android.location.Location?,
    onExplorar: () -> Unit,
    onPerfil: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Título Principal ---
        Text(
            text = "✨ ¡BIENVENIDO A LEVEL-UP! ✨",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        // --- Grilla de Categorías (Tu diseño anterior) ---
        Text(
            text = "Explora nuestras categorías",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start).padding(bottom = 16.dp)
        )

        // Botones de categorías principales
        CategoryButton("JUEGOS", Icons.Default.VideogameAsset) { onExplorar() }
        Spacer(modifier = Modifier.height(10.dp))
        CategoryButton("CONSOLAS", Icons.Default.Gamepad) { /* Navegar a consolas */ }
        Spacer(modifier = Modifier.height(10.dp))
        CategoryButton("ACCESORIOS", Icons.Default.Headset) { /* Navegar a accesorios */ }
        Spacer(modifier = Modifier.height(10.dp))
        CategoryButton("FIGURAS", Icons.Default.EmojiEvents) { /* Navegar a figuras */ }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de Perfil/Login en la parte inferior
        Button(
            onClick = onPerfil,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("IR A MI PERFIL")
        }
    }
}

@Composable
fun CategoryButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(70.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Text(text, style = MaterialTheme.typography.titleLarge)
        }
    }
}