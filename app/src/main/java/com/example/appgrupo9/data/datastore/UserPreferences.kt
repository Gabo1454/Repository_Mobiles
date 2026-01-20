package com.example.appgrupo9.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val ROLE_ID = intPreferencesKey("role_id")
        val CODIGO_DESCUENTO = stringPreferencesKey("codigo_descuento")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val USERNAME = stringPreferencesKey("username")
    }

    // --- LECTURA DE DATOS (FLOWS) ---

    val darkMode: Flow<Boolean> = dataStore.data.map { it[DARK_MODE] ?: true }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { it[IS_LOGGED_IN] ?: false }

    val authToken: Flow<String?> = dataStore.data.map { it[AUTH_TOKEN] }

    val username: Flow<String> = dataStore.data.map { it[USERNAME] ?: "Gamer" }

    // 🔥 Detecta si el usuario es Admin (ID 1 en Postgres)
    val isAdmin: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[ROLE_ID] == 1
    }

    // para que no de error en otras pantallas aunque no se use
    val codigoDescuento: Flow<String?> = dataStore.data.map { it[CODIGO_DESCUENTO] }

    // --- ESCRITURA DE DATOS (SUSPEND FUNCTIONS) ---

    suspend fun saveToken(token: String) {
        dataStore.edit { it[AUTH_TOKEN] = token }
    }

    suspend fun saveUsername(name: String) {
        dataStore.edit { it[USERNAME] = name }
    }

    // LoginViewModel
    suspend fun saveAdminStatus(isAdmin: Boolean) {
        dataStore.edit { prefs ->
            prefs[ROLE_ID] = if (isAdmin) 1 else 2
        }
    }

    // función para que no de error "Unresolved reference"
    suspend fun guardarCodigo(codigo: String) {
        dataStore.edit { it[CODIGO_DESCUENTO] = codigo }
    }

    suspend fun setLoggedIn(value: Boolean) {
        dataStore.edit { it[IS_LOGGED_IN] = value }
    }

    suspend fun logout() {
        dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = false
            prefs[AUTH_TOKEN] = ""
            prefs[USERNAME] = ""
            prefs[ROLE_ID] = 0
            prefs[CODIGO_DESCUENTO] = ""
        }
    }
}