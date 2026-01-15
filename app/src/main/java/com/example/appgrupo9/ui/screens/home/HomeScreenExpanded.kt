package com.example.appgrupo9.ui.screens.home

import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.appgrupo9.ui.components.layout.GlobalScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpanded(
    ubicacion: Location?,
    onExplorar: () -> Unit,
    onPerfil: () -> Unit
) {
    GlobalScaffold { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeContent(
                ubicacion = ubicacion,
                onExplorar = onExplorar,
                onPerfil = onPerfil
            )
        }
    }
}
