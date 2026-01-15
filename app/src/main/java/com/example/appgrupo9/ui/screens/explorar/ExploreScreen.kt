package com.example.appgrupo9.ui.screens.explorar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExploreScreen(
    onProductClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Explora nuestra tienda",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Descubre nuevos juegos y ofertas",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(onClick = { onProductClick("featured_item") }) {
            Text("View featured product")
        }
    }
}
