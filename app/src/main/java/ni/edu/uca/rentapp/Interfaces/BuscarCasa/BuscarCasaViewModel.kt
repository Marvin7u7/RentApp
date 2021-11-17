package ni.edu.uca.rentapp.Interfaces.BuscarCasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuscarCasaViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment Arrendatario"
    }
    val text: LiveData<String> = _text
}