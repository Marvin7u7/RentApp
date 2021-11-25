package ni.edu.uca.rentapp.Interfaces.Usuario.edicionperfil

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ni.edu.uca.rentapp.Entidades.departamentoDao
import ni.edu.uca.rentapp.Entidades.departamentos
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.Entidades.usuarioDao
import ni.edu.uca.rentapp.InmueblesViewModel
import java.lang.IllegalArgumentException

class EditarUsuarioViewModel(private val user: usuarioDao) : ViewModel() {
    private fun editarUsuario(userD: usuario){
        viewModelScope.launch {
            Log.e("INFO editarUsuarioVM:", userD.apellido)
            user.editarUsuario(
                userD.idUsuario,
                userD.nombre,
                userD.apellido,
                userD.tipoUsuario,
                userD.email,
                userD.cedula,
                userD.password,
                userD.telefono,
                userD.foto
            )
        }
    }

    fun isEntryValid(
        idUsuario: Int,
        nombre: String,
        apellido: String,
        tipoUsuario: String,
        email: String,
        cedula: String,
        password: String,
        telefono: String,
        foto: String
    ): Boolean {
        Log.e("INFO isEntryValidVM:", apellido)
        if(nombre.isBlank() || apellido.isBlank() || tipoUsuario.isBlank() || email.isBlank() || cedula.isBlank() || password.isBlank() || telefono.isBlank()){
            return false
        }
        return true
    }

    private fun getNewUserEntry(
        idUsuario: Int,
        nombre: String,
        apellido: String,
        tipoUsuario: String,
        email: String,
        cedula: String,
        password: String,
        telefono: String,
        foto: String
    ): usuario {
        Log.e("INFO getNewUserEntryVM:", apellido)
        return usuario(
            idUsuario = idUsuario,
            nombre = nombre,
            apellido = apellido,
            tipoUsuario = tipoUsuario,
            email = email,
            cedula = cedula,
            password = password,
            telefono = telefono,
            casaFav = 0,
            foto = foto
        )
    }

    fun refactorUser(
        idUsuario: Int,
        nombre: String,
        apellido: String,
        tipoUsuario: String,
        email: String,
        cedula: String,
        password: String,
        telefono: String,
        foto: String
    ){
        Log.e("INFO refactorUserVM:", apellido)
        val newUser = getNewUserEntry(idUsuario, nombre, apellido, tipoUsuario, email, cedula, password, telefono, foto)
        editarUsuario(newUser)
    }
}

class EditarUsuarioViewModelFactory(private val user: usuarioDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditarUsuarioViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return EditarUsuarioViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}