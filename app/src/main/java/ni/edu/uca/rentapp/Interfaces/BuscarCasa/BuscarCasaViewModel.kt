package ni.edu.uca.rentapp.Interfaces.BuscarCasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.Entidades.Casa
import ni.edu.uca.rentapp.Entidades.casasDao

class BuscarCasaViewModel(private val casa: casasDao) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment Arrendatario"
    }
    val text: LiveData<String> = _text

     private fun seleccionarCasas(): LiveData<List<Casa>> {
        return casa.traerCasas()
    }

    private fun obtenerCasaPorId(id_casa : Int) : Casa = casa.obtenerCasaPorId(id_casa)

    fun getAllCasas() = seleccionarCasas()

    fun getCasaById(id_casa : Int) = obtenerCasaPorId(id_casa)


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