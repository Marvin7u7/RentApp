package ni.edu.uca.rentapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ni.edu.uca.rentapp.Entidades.departamentoDao
import ni.edu.uca.rentapp.Entidades.departamentos
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.Entidades.usuarioDao
import java.lang.IllegalArgumentException

class InmueblesViewModel(private val depDao: departamentoDao) : ViewModel() {
    private fun traerDeps(): List<String> {
        return depDao.seleccionarDepartamentos()
    }

    fun fachada(): List<String>{
        return traerDeps()
    }
}

class ImueblesViewModelFactory(private val departamentos: departamentoDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InmueblesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return InmueblesViewModel(departamentos) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}