package com.example.appgrupo9.ui.screens.home

import android.location.Location
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeInfo(ubicacion: Location?) {
    ubicacion?.let {
        Text("Lat: ${it.latitude}")
        Text("Lon: ${it.longitude}")
    }
}