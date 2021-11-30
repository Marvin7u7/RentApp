package ni.edu.uca.rentapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ni.edu.uca.rentapp.Entidades.*
import java.lang.IllegalArgumentException

class InmueblesViewModel(private val depDao: departamentoDao, private val casas: casasDao) : ViewModel() {
    private fun traerDeps(): List<String> {
        return depDao.seleccionarDepartamentos()
    }

    private fun insertCasa(casa: Casa){
        viewModelScope.launch {
            Log.e("insertarCasaNoDao", casa.precioMes)
            casas.insertarCasa(casa)
        }
    }

    fun fachada(): List<String>{
        return traerDeps()
    }

    fun isEntryValid(
        pisos: String,
        cuartos: String,
        baños: String,
        precioMes: String,
        descripcionG: String,
        descripcionB: String,
        departamento: Int,
        foto: String,
        propietario: Int
    ): Boolean {
        if(pisos.isBlank() || cuartos.isBlank() || baños.isBlank() || precioMes.isBlank() || descripcionG.isBlank() || descripcionB.isBlank() || departamento == 0 || foto.isBlank() || propietario == 0){
            Log.e("isEntryValidBack", pisos)
            Log.e("isEntryValidBack", cuartos)
            Log.e("isEntryValidBack", baños)
            Log.e("isEntryValidBack", precioMes)
            Log.e("isEntryValidBack", descripcionG)
            Log.e("isEntryValidBack", descripcionB)
            Log.e("isEntryValidBack", departamento.toString())
            Log.e("isEntryValidBack", foto)
            Log.e("isEntryValidBack", propietario.toString())
            return false
        }
        return true
    }

    private fun getNewHouseEntry(
        pisos: String,
        cuartos: String,
        baños: String,
        precioMes: String,
        descripcionG: String,
        descripcionB: String,
        departamento: Int,
        foto: String,
        propietario: Int
    ): Casa {
        Log.e("getNewEntryHouseBack", precioMes)
        return Casa(
            pisos = pisos,
            cuartos = cuartos,
            baños = baños,
            precioMes = precioMes,
            descripcionG = descripcionG,
            descripcionB = descripcionB,
            departamento = departamento,
            foto = foto,
            propietario = propietario
        )
    }

    fun addNewHouse(
        pisos: String,
        cuartos: String,
        baños: String,
        precioMes: String,
        descripcionG: String,
        descripcionB: String,
        departamento: Int,
        foto: String,
        propietario: Int
    ){
        val newHouse = getNewHouseEntry(pisos, cuartos, baños, precioMes, descripcionG, descripcionB, departamento, foto, propietario)
        insertCasa(newHouse)
        Log.e("addNewHouseBack", precioMes)
    }

}

class ImueblesViewModelFactory(private val departamentos: departamentoDao, private val casas: casasDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InmueblesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return InmueblesViewModel(departamentos, casas) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}