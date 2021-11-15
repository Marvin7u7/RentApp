package ni.edu.uca.rentapp.Interfaces.Usuario.edicionperfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.rentapp.R

class Editar_usuario : Fragment() {

    companion object {
        fun newInstance() = Editar_usuario()
    }

    private lateinit var viewModel: EditarUsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.editar_usuario_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditarUsuarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}