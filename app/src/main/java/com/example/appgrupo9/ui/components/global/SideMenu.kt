package com.example.appgrupo9.ui.components.global

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch

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
