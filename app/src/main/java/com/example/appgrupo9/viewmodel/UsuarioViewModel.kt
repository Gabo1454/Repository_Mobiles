package com.example.appgrupo9.viewmodel

import UsuarioUiState
import androidx.lifecycle.ViewModel
import com.example.appgrupo9.domain.validation.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    // Estado interno mutable
    private val _estado = MutableStateFlow(UsuarioUiState())

    // Estado expuesto para la UI
    val estado: StateFlow<UsuarioUiState> = _estado

    // Actualizar Username
    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    // Actualizar Nombre Completo (Nuevo campo coherente con Backend)
    fun onFullNameChange(valor: String) {
        _estado.update { it.copy(fullName = valor, errores = it.errores.copy(fullName = null)) }
    }

    // Actualizar Correo
    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    // Actualizar Clave
    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    // Actualizar Dirección
    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    // Actualizar checkbox
    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptarTerminos = valor) }
    }

    // Validación global del formulario
    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            fullName = if (estadoActual.fullName.isBlank()) "El nombre completo es obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo inválido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        // Verificamos si hay algún mensaje de error en la lista
        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.fullName,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores && estadoActual.aceptarTerminos
    }
}