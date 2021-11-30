package ni.edu.uca.rentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ni.edu.uca.rentapp.Interfaces.BuscarCasa.buscar_casaArgs
import ni.edu.uca.rentapp.databinding.VerCasaFragmentBinding

class VerCasaFragment : Fragment() {

    private val viewModel : VerCasaViewModel by activityViewModels {
        VerCasaViewModelFactory((activity?.application as RentAppAplication).database.casaDao())
    }


    private var _binding: VerCasaFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = VerCasaFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = VerCasaFragmentBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument: buscar_casaArgs by navArgs()
        val clickedCasa = viewModel.getCasaById(argument.casaId)

        with(binding) {
            tvDescripcion.text = clickedCasa.descripcionB
        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(VerCasaViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}