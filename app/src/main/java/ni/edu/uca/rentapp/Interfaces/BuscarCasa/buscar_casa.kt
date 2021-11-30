package ni.edu.uca.rentapp.Interfaces.BuscarCasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.rentapp.Entidades.Casa
import ni.edu.uca.rentapp.OnCasaViewHolderElementClick
import ni.edu.uca.rentapp.R
import ni.edu.uca.rentapp.RentAppAplication
import ni.edu.uca.rentapp.adapter.CasaAdapter
import ni.edu.uca.rentapp.databinding.BuscarCasaFragmentBinding

class buscar_casa : Fragment(), OnCasaViewHolderElementClick {
    private val viewModel : BuscarCasaViewModel by activityViewModels {
        BuscarCasaViewModelFactory((activity?.application as RentAppAplication).database.casaDao())
    }

    private var _binding: BuscarCasaFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        buscarcasaViewModel =
//            ViewModelProvider(this)[BuscarCasaViewModel::class.java]

        _binding = BuscarCasaFragmentBinding.inflate(inflater, container, false)

        val departamentos = resources.getStringArray(R.array.departamentos)
//        val adapter = activity?.let {
//            ArrayAdapter(
//                it,
//                R.layout.list_departamentos,
//                departamentos
//            )
//        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllCasas().observe(viewLifecycleOwner) {
            casas ->
            casas.let {
                binding.recyclerCasas.apply {
                    layoutManager = LinearLayoutManager(activity)

                    adapter = CasaAdapter(this.context, casas, this@buscar_casa)
                }
            }
        }

    }

    override fun onClick(casa: Casa) {
            val action = buscar_casaDirections.actionNavBuscarCasaToVerCasaFragment(casaId = casa.idCasa)
            findNavController().navigate(action)
    }


}