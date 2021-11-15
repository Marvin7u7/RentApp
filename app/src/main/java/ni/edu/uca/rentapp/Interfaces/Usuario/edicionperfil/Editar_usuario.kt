package ni.edu.uca.rentapp.Interfaces.Usuario.edicionperfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.databinding.EditarUsuarioFragmentBinding

class Editar_usuario : Fragment() {

    private lateinit var editarusuarioViewModel: EditarUsuarioViewModel
    private var _binding: EditarUsuarioFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editarusuarioViewModel =
            ViewModelProvider(this).get(EditarUsuarioViewModel::class.java)

        _binding = EditarUsuarioFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}