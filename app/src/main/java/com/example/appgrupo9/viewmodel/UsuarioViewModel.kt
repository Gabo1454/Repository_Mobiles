package com.example.appgrupo9.viewmodel

import UsuarioUiState
import androidx.lifecycle.ViewModel
import com.example.appgrupo9.model.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    //Estado interno mutable
    private val _estado = MutableStateFlow(UsuarioUiState())

    //Estado expuesto para la UI
    val estado: StateFlow<UsuarioUiState> = _estado

    //Actualizar el campo nombre y limpia su error
    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }
    //Actualizar el campo correo
    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null))}
    }
    //Actualizar el campo clave
    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null))}
    }
    //Actualizar el campo clave
    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null))}
    }
    //Actualizar checkbox de aceptacion
    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptarTerminos = valor) }
    }

    //esto va aqui? si no se mueve xd
    //Validacion global del formulario
    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo invalido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 5 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.direccion,
        ). isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }
}