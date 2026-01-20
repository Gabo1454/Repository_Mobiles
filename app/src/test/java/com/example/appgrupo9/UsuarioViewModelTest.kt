package com.example.appgrupo9
//Test Pass: Se procesan correctamente los 4 campos que pide el backend (username, email, password y fullname)

import com.example.appgrupo9.viewmodel.UsuarioViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UsuarioViewModelTest {
    private lateinit var viewModel: UsuarioViewModel

    @Before
    fun setup() {
        // Inicializamos el ViewModel antes de cada prueba
        viewModel = UsuarioViewModel()
    }

    @Test
    fun `validarFormulario debe retornar falso cuando los campos estan vacios`() {
        // GIVEN: El estado inicial tiene campos en blanco

        // WHEN: Ejecutamos la validación
        val esValido = viewModel.validarFormulario()

        // THEN: Debe ser falso y los errores no deben ser nulos
        assertFalse(esValido)
        val estadoActual = viewModel.estado.value
        assertEquals("Campo obligatorio", estadoActual.errores.nombre)
        assertEquals("Campo obligatorio", estadoActual.errores.direccion)
    }

    @Test
    fun `validarFormulario debe retornar falso si el correo no tiene formato correcto`() {
        // GIVEN: Datos correctos pero correo sin @
        viewModel.onNombreChange("Juan Perez")
        viewModel.onCorreoChange("correo_sin_arroba.com")
        viewModel.onClaveChange("123456")
        viewModel.onDireccionChange("Calle Falsa 123")

        // WHEN
        val esValido = viewModel.validarFormulario()

        // THEN
        assertFalse(esValido)
        assertEquals("Correo inválido", viewModel.estado.value.errores.correo)
    }

    @Test
    fun `validarFormulario debe retornar verdadero cuando todos los datos son validos`() {
        // GIVEN: Todos los campos llenos correctamente
        viewModel.onNombreChange("Gamer Profesional")
        viewModel.onFullNameChange("Gabriel Garcia")
        viewModel.onCorreoChange("contacto@gamer.com")
        viewModel.onClaveChange("clave123")
        viewModel.onDireccionChange("Avenida Siempre Viva 742")
        viewModel.onAceptarTerminosChange(true)

        // WHEN
        val esValido = viewModel.validarFormulario()

        // THEN
        assertTrue(esValido)
        // Verificamos que no haya mensajes de error
        val errores = viewModel.estado.value.errores
        assertTrue(errores.nombre == null)
        assertTrue(errores.correo == null)
    }
}