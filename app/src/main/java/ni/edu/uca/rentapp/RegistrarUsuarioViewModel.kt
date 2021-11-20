package ni.edu.uca.rentapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.coroutines.launch
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.Entidades.usuarioDao
import java.lang.IllegalArgumentException

class RegistrarUsuarioViewModel(private val usuarioD: usuarioDao) : ViewModel() {
    private fun insertUsuario(user: usuario){
        viewModelScope.launch {
            usuarioD.insertarUsuario(user)
        }
    }

    fun isEntryValid(
        nombre: String,
        apellido: String,
        tipoUsuario: String,
        email: String,
        cedula: String,
        password: String,
        telefono: String
    ): Boolean {
        if(nombre.isBlank() || apellido.isBlank() || tipoUsuario.isBlank() || email.isBlank() || cedula.isBlank() || password.isBlank() || telefono.isBlank()){
            return false
        }
        return true
    }

    private fun getNewUserEntry(
        nombre: String,
        apellido: String,
        tipoUsuario: String,
        email: String,
        cedula: String,
        password: String,
        telefono: String
    ): usuario {
        return usuario(
            nombre = nombre,
            apellido = apellido,
            tipoUsuario = tipoUsuario,
            email = email,
            cedula = cedula,
            password = password,
            telefono = telefono,
            casaFav = 0
        )
    }

    fun addNewUser(
        nombre: String,
        apellido: String,
        tipoUsuario: String,
        email: String,
        cedula: String,
        password: String,
        telefono: String
    ){
        val newUser = getNewUserEntry(nombre, apellido, tipoUsuario, email, cedula, password, telefono)
        insertUsuario(newUser)
    }
}

class RegistrarUsuarioViewModelFactory(private val usuarioD: usuarioDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegistrarUsuarioViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RegistrarUsuarioViewModel(usuarioD) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}