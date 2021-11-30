package ni.edu.uca.rentapp

import androidx.lifecycle.*
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.Entidades.usuarioDao
import java.lang.IllegalArgumentException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ni.edu.uca.rentapp.Entidades.departamentoDao


class LoginViewModel(private val usuarioD: usuarioDao, private val depas: departamentoDao) : ViewModel() {
    private fun searchByEP(email: String, password: String): usuario{
        return usuarioD.loginUsuario(email, password)
    }

    private fun createDeps(){
        depas.crearDepas()
    }

    private fun verificarCantDeps(): List<String>{
        return depas.verificarTabla()
    }

    fun bringDeps(): List<String> {
        return verificarCantDeps()
    }

    fun crearDepsFront(){
        createDeps()
    }

    fun isEntryValid(
        email: String,
        password: String
    ): Boolean {
        if(email.isBlank() || password.isBlank()){
            return false
        }
        return true
    }

    fun searchUser(
        email: String,
        password: String
    ): usuario{
        return searchByEP(email, password)
    }
}

class LoginViewModelFactory(private val usuarioD: usuarioDao, private val depas: departamentoDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(usuarioD, depas) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}