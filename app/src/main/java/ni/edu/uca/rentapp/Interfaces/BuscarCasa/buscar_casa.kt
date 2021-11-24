package ni.edu.uca.rentapp.Interfaces.BuscarCasa

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ni.edu.uca.rentapp.R
import ni.edu.uca.rentapp.databinding.BuscarCasaFragmentBinding

class buscar_casa : Fragment() {
    private lateinit var buscarcasaViewModel: BuscarCasaViewModel
    private var _binding: BuscarCasaFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        buscarcasaViewModel =
            ViewModelProvider(this).get(BuscarCasaViewModel::class.java)

        _binding = BuscarCasaFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val departamentos = resources.getStringArray(R.array.departamentos)
        val adapter = activity?.let {
            ArrayAdapter(
                it,
                R.layout.list_departamentos,
                departamentos
            )
        }

        return root
    }



}