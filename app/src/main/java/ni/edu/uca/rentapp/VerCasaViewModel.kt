package ni.edu.uca.rentapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.Entidades.*

class VerCasaViewModel(private val casaDao : casasDao, private val user: usuarioDao) : ViewModel() {
    private fun seleccionarCasas(): LiveData<List<Casa>> {
        return casaDao.traerCasas()
    }

    private fun traerPropietario(id: Int): usuario {
        return user.propietario(id)
    }

    fun isEntryValid(id: Int): Boolean{
        if(id == 0){
            return false
        }
        return true
    }

    fun getUserById(id: Int): usuario {
        return traerPropietario(id)
    }

    private fun obtenerCasaPorId(id_casa : Int) : Casa = casaDao.obtenerCasaPorId(id_casa)

    fun getAllCasas() = seleccionarCasas()

    fun getCasaById(id_casa : Int) = obtenerCasaPorId(id_casa)
}


class VerCasaViewModelFactory(private val casa: casasDao, private val user: usuarioDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VerCasaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return VerCasaViewModel(casa, user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}