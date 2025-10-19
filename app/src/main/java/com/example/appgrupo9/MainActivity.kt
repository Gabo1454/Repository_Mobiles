package com.example.appgrupo9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.appgrupo9.ui.screens.HomeScreenWithDrawer
import com.example.appgrupo9.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // Crear ViewModel
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                // Llamamos al composable con drawer
                HomeScreenWithDrawer(viewModel
                = homeViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HamburgerMenuButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Abrir menú"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenu(
    drawerContent: @Composable ColumnScope.() -> Unit,
    mainContent: @Composable (openDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column { drawerContent() }
            }
        }
    ) {
        mainContent {
            scope.launch {
                if (drawerState.isClosed) drawerState.open() else drawerState.close()
            }
        }
    }
}