package com.example.appgrupo9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.appgrupo9.ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        setContent {
            MaterialTheme {
                //Llamamos a HomeScreen, que ya define toda la UI
                HomeScreen()
            }
        }
    }
}
