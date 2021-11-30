package ni.edu.uca.rentapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.Entidades.Casa
import ni.edu.uca.rentapp.Entidades.casasDao

class VerCasaViewModel(private val casaDao : casasDao) : ViewModel() {
    private fun seleccionarCasas(): LiveData<List<Casa>> {
        return casaDao.traerCasas()
    }

    private fun obtenerCasaPorId(id_casa : Int) : Casa = casaDao.obtenerCasaPorId(id_casa)

    fun getAllCasas() = seleccionarCasas()

    fun getCasaById(id_casa : Int) = obtenerCasaPorId(id_casa)
}


class VerCasaViewModelFactory(private val casa: casasDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VerCasaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return VerCasaViewModel(casa) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}