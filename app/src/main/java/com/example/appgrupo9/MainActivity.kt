package com.example.appgrupo9
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.appgrupo9.ui.screens.HomeScreen2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                //Llamamos a HomeScreen, que ya define toda la UI
                HomeScreen2()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    MaterialTheme {
        HomeScreen2()
    }
}