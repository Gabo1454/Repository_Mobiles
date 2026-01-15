package com.example.appgrupo9.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val CODIGO_DESCUENTO = stringPreferencesKey("codigo_descuento")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val darkMode: Flow<Boolean> =
        dataStore.data.map { it[DARK_MODE] ?: true }

    val codigoDescuento: Flow<String?> =
        dataStore.data.map { it[CODIGO_DESCUENTO] }

    val isLoggedIn: Flow<Boolean> =
        dataStore.data.map { it[IS_LOGGED_IN] ?: false }

    suspend fun setLoggedIn(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = value
        }
    }


    suspend fun guardarCodigo(codigo: String) {
        dataStore.edit { prefs ->
            prefs[CODIGO_DESCUENTO] = codigo
        }
    }
}
