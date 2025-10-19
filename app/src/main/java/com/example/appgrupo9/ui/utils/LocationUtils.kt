package com.example.appgrupo9.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

fun tienePermisoUbicacion(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

fun obtenerUbicacion(context: Context, onUbicacionObtenida: (Location?) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    try {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                onUbicacionObtenida(location)
            }
    } catch (e: SecurityException) {
        onUbicacionObtenida(null)
    }
}

/*  Tal parece que no es modular esto ¿Puedes verlo jose?
*
*     //Acceso a ubicacion
    val context = LocalContext.current
    var permisoConcedido by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permisoConcedido = isGranted
    }

    LaunchedEffect(Unit) {
        val permiso = Manifest.permission.ACCESS_FINE_LOCATION
        val estado = ContextCompat.checkSelfPermission(context, permiso)

        if (estado != PackageManager.PERMISSION_GRANTED) {
            launcher.launch(permiso)
        } else {
            permisoConcedido = true
        }
    }

    LaunchedEffect(permisoConcedido) {
        if (permisoConcedido) {
            obtenerUbicacion(context) { location ->
                location?.let {
                    println("Latitud: ${it.latitude}, Longitud: ${it.longitude}")
                    // Aquí puedes guardar, mostrar o usar la ubicación
                }
            }
        }
    }

    if (permisoConcedido) {
        val windowSizeClass = obtenerWindowsSizeClass()
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> HomeScreenCompact()
            WindowWidthSizeClass.Medium  -> HomeScreenCompact()
            WindowWidthSizeClass.Expanded -> HomeScreenExpanded()
            else -> HomeScreenCompact()
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("📍 Solicitando permiso de ubicación...")
        }
    }
*
* */