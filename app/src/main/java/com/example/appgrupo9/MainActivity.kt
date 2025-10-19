package com.example.appgrupo9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.appgrupo9.ui.screens.HomeScreenWithDrawer
import com.example.appgrupo9.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    // Crear ViewModel
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                // Llamamos al composable con drawer
                HomeScreenWithDrawer(viewModel = homeViewModel)
            }
        }
    }
}
