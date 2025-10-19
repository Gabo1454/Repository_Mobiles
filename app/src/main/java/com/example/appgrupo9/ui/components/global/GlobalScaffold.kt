package com.example.appgrupo9.ui.components.global

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appgrupo9.R

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
