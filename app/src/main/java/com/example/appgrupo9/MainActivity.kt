package com.example.appgrupo9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.example.appgrupo9.data.datastore.UserPreferences
import com.example.appgrupo9.navigation.AppNavGraph
import com.example.appgrupo9.ui.theme.GaymerTheme

// 🔹 Esta extensión ya existe en tu DataStore, no necesitas otra
// private val Context.userDataStore by preferencesDataStore(name = "user_prefs")
// ya tienes: private val Context.dataStore by preferencesDataStore("user_prefs") en UserPreferences

class MainActivity : ComponentActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 🔹 Inicializa UserPreferences usando la propiedad 'dataStore' que ya existe
        userPreferences = UserPreferences(this)

        setContent {
            GaymerTheme {
                val navController = rememberNavController()

                AppNavGraph(
                    navController = navController,
                    userPreferences = userPreferences // ahora compila
                )
            }
        }
    }
}
