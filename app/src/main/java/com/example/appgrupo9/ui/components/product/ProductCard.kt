package com.example.appgrupo9.ui.components.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.data.model.Product

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    actionLabel: String,
    onCardClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            ProductImage(
                imageUrl = product.image,
                contentDescription = product.name
            )

            Column(modifier = Modifier.padding(12.dp)) {
                ProductInfo(
                    name = product.name,
                    price = product.price
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onActionClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(actionLabel)
                }
            }
        }
    }
}
