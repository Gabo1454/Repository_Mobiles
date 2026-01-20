package com.example.appgrupo9.ui.screens.explorar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appgrupo9.ui.components.product.ProductCard
import com.example.appgrupo9.viewmodel.ExploreViewModel

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = viewModel(),
    // Mantenemos Int para total compatibilidad con PostgreSQL y el ID del producto
    onProductClick: (Int) -> Unit = {}
) {
    val products by viewModel.products.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Explora nuestra tienda",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Descubre nuevos juegos y ofertas",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    modifier = Modifier.padding(8.dp),
                    actionLabel = "Agregar",
                    onCardClick = {
                        onProductClick(product.id)
                    },
                    onActionClick = {
                        // Acción secundaria (ej: agregar al carrito)
                    }
                )
            }
        }
    }
}
