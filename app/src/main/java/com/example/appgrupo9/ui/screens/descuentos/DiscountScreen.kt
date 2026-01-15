package com.example.appgrupo9.ui.screens.descuentos

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiscountScreen(
    onApplyCode: (String) -> Unit = {}
) {
    var discountCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Discount Code",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = discountCode,
            onValueChange = { discountCode = it },
            label = { Text("Enter your code") },
            singleLine = true
        )

        Button(
            onClick = { onApplyCode(discountCode) },
            enabled = discountCode.isNotBlank()
        ) {
            Text("Apply discount")
        }
    }
}