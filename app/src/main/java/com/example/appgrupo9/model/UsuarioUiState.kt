import com.example.appgrupo9.model.UsuarioErrores

//Modelo principal que representa el estado del formulario del usuario
data class UsuarioUiState(
    val nombre: String = " ",
    val correo: String = " ",
    val clave : String = " ",
    val direccion: String = " ",
    val aceptarTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores() //Objeto que contiene los errores
)