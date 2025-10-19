package com.example.appgrupo9.ui.components.global

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.material3.ModalNavigationDrawer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HamburgerMenuButton(isOpen: MutableState<Boolean>) {
    IconButton(onClick = {isOpen.value = !isOpen.value}) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenu(
    isOpen: MutableState<Boolean>,
    drawerContent: @Composable ColumnScope.() -> Unit,
    mainContent: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(
        if (isOpen.value) DrawerValue.Open else DrawerValue.Closed
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column { drawerContent() }
        },
        content = {
            mainContent()
        }
    )
}