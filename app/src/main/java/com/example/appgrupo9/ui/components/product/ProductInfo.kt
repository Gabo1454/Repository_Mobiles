package com.example.appgrupo9.ui.components.product

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductInfo(
    name: String,
    price: Int
) {
    val formattedPrice = NumberFormat
        .getNumberInstance(Locale("es", "CL"))
        .format(price)

    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )

    Text(
        text = "$$formattedPrice CLP",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}
