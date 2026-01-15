package com.example.appgrupo9.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.location.Location


@Composable
fun HomeContent(
    ubicacion: Location?,
    onExplorar: () -> Unit,
    onPerfil: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeHeader()

        HomeActions(
            onExplorar = onExplorar,
            onPerfil = onPerfil
        )

        HomeInfo(ubicacion)
    }
}
