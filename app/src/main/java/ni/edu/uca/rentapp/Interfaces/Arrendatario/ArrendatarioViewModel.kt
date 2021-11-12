package ni.edu.uca.rentapp.Interfaces.Arrendatario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArrendatarioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este es el Fragment Arrendatario"
    }
    val text: LiveData<String> = _text
}