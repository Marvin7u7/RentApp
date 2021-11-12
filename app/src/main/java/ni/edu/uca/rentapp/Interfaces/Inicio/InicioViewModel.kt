package ni.edu.uca.rentapp.Interfaces.Inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InicioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment de Inicio"
    }
    val text: LiveData<String> = _text
}