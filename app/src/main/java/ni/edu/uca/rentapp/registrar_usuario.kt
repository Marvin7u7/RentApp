package ni.edu.uca.rentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.Interfaces.Usuario.UsuarioViewModel
import ni.edu.uca.rentapp.databinding.FragmentRegistrarUsuarioBinding
import ni.edu.uca.rentapp.databinding.FragmentUsuarioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registrar_usuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class registrar_usuario : Fragment() {
    private lateinit var registrar: registrarViewModel
    private var _binding: FragmentRegistrarUsuarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // registrar =
       //     ViewModelProvider(this).get(registrarViewModel::class.java)

        _binding = FragmentRegistrarUsuarioBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}