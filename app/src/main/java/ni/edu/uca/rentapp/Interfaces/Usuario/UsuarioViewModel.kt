package ni.edu.uca.rentapp.Interfaces.Usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment Usuario"
    }
    val text: LiveData<String> = _text
}