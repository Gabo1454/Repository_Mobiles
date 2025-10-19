package com.example.appgrupo9.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.appgrupo9.R
import com.example.appgrupo9.ui.utils.obtenerWindowsSizeClass
import com.google.android.gms.location.LocationServices

// --- Función adaptativa que decide qué layout mostrar según el tamaño de pantalla ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val windowSizeClass = obtenerWindowsSizeClass()

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompact()
        WindowWidthSizeClass.Medium  -> HomeScreenCompact()
        WindowWidthSizeClass.Expanded -> HomeScreenExpanded()
        else -> HomeScreenCompact()
    }
    //Acceso a ubicacion
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


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalScaffold(content: @Composable (PaddingValues) -> Unit){
    Scaffold (
        //barra superior global
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        //Logo a la izquierda
                        Image(
                            painter = painterResource(id = R.drawable.logo_level_up),
                            contentDescription = "Logo LEVEL-UP",
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // Nombre de la tienda
                        Text(
                            text = "LEVEL-UP GAMER",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.weight(1f)) //Empujar iconos a la derecha

                        //Icono de busqueda sin utilidad de momento
                        IconButton(onClick = {/*Desplegar barra*/}) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar"
                            )
                        }

                        //Icono de carrito sin funcionalidad
                        IconButton(onClick = {/*Abrir carrito*/}) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Carrito"
                            )
                        }
                    }
                },
                //Colores
                /*
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ) */
            )
        },
        content = content
    )
}


// --- Layout para pantallas pequeñas (Compact) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompact() {
    GlobalScaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultHomeContent()
        }
    }
    }

// --- Layout para pantallas grandes (Expanded) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpanded() {
    GlobalScaffold { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultHomeContent()
        }
    }
}

// --- Contenido común reutilizable ---
@Composable
fun DefaultHomeContent() {
    Text("¡Bienvenido!", color = MaterialTheme.colorScheme.onPrimary)

    Button(onClick = {}) {
        Text("Presioname")
    }

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo App",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentScale = ContentScale.Fit
    )

    Text("Explora Compose y Material3", color = MaterialTheme.colorScheme.secondary)

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = true, onCheckedChange = {})
        Text("Acepto los términos")
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Modo oscuro")
        Switch(checked = false, onCheckedChange = {})
    }
}

// --- Previews para Android Studio ---

@Preview(name = "Compact", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompact() {
    HomeScreenCompact()
}

@Preview(name = "Medium", widthDp = 600, heightDp = 800)
@Composable
fun PreviewMedium() {
    HomeScreenCompact() // o un layout específico para Medium si lo tienes
}

@Preview(name = "Expanded", widthDp = 840, heightDp = 900)
@Composable
fun PreviewExpanded() {
    HomeScreenExpanded()
}

// --- Función para obtener la ubicación ---
fun obtenerUbicacion(context: Context, onUbicacionObtenida: (Location?) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    try {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                onUbicacionObtenida(location)
            }
    } catch (e: SecurityException) {
        onUbicacionObtenida(null)
    }
}
