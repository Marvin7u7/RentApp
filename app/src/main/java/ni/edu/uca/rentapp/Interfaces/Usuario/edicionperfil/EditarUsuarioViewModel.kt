package ni.edu.uca.rentapp.Interfaces.Usuario.edicionperfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditarUsuarioViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment de Editar"
    }
    val text: LiveData<String> = _text
}