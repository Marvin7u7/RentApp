package ni.edu.uca.rentapp.Interfaces.BuscarCasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.Entidades.casas
import ni.edu.uca.rentapp.Entidades.casasDao
import ni.edu.uca.rentapp.Entidades.usuarioDao
import ni.edu.uca.rentapp.LoginViewModel
import java.lang.IllegalArgumentException

class BuscarCasaViewModel(private val casa: casasDao) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment Arrendatario"
    }
    val text: LiveData<String> = _text

    private fun seleccionarCasas(): List<casas>{
        return casa.traerCasas()
    }
}

class BuscarCasaViewModelFactory(private val casa: casasDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BuscarCasaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BuscarCasaViewModel(casa) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}