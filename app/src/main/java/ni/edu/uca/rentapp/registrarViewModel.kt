package ni.edu.uca.rentapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ni.edu.uca.rentapp.Entidades.Usuario
import ni.edu.uca.rentapp.Entidades.usuarioDao

class registrarViewModel(private val userDao: usuarioDao): ViewModel(){
    private fun insertUser(usuario: Usuario){
        viewModelScope.launch {
            userDao.insertUser(usuario)
        }
    }

    private fun getNewUserEntry(
        nombre: String,
        apellido:String,
        tipoUser: Int,
        email: String,
        numeroCedula: String,
        contraseña: String,
        numeroTelefono: String
        ): Usuario {
        return Usuario(
            nombre = nombre,
            apellido = apellido,
            tipoUsuario = tipoUser,
            email = email,
            numeroCedula = numeroCedula,
            contraseña = contraseña,
            numeroTelefono = numeroTelefono
        )
    }

    fun addNewUser(
        nombre: String,
        apellido:String,
        tipoUser: Int,
        email: String,
        numeroCedula: String,
        contraseña: String,
        numeroTelefono: String
    ){
        val newUser = getNewUserEntry(nombre, apellido, tipoUser, email, numeroCedula, contraseña, numeroTelefono)
        insertUser(newUser)
    }
}

class registrarViewModelFactory(private val userDao: usuarioDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(registrarViewModel::class.java)){
            @Suppress("UNCHEKED_CAST")
            return registrarViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}