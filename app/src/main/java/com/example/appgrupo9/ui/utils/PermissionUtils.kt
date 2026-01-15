package com.example.appgrupo9.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

fun tienePermisoUbicacion(context: Context): Boolean =
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

fun obtenerUbicacion(
    context: Context,
    onUbicacionObtenida: (Location?) -> Unit
) {
    val client = LocationServices.getFusedLocationProviderClient(context)
    try {
        client.lastLocation.addOnSuccessListener { location ->
            onUbicacionObtenida(location)
        }
    } catch (e: SecurityException) {
        onUbicacionObtenida(null)
    }
}
