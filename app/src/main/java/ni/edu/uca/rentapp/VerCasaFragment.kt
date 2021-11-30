package ni.edu.uca.rentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        var casaId = 0
        arguments?.let {
            val safeArgs = VerCasaFragmentArgs.fromBundle(it)
            casaId = safeArgs.casaId
        }
        Toast.makeText(getActivity(), "${casaId}", Toast.LENGTH_SHORT).show()
        //val clickedCasa = viewModel.getCasaById(argument.casaId)

        lifecycleScope.launch(){
            withContext(Dispatchers.IO){
                val clickCasa = viewModel.getCasaById(casaId)
                try {
                    lifecycleScope.launch(){
                        withContext(Dispatchers.Main){
                            with(binding) {
                                imgCasa.setImageURI(clickCasa.foto.toUri())
                                tvDescripcion.text = clickCasa.descripcionB
                                tvBathroomsTitle.text = clickCasa.baños
                                tvCuartosTitle.text = clickCasa.cuartos
                                tvPrecioCasa.text = clickCasa.precioMes
                            }
                        }
                    }
                }catch (ex: Exception){
                    Log.e("ERROR", ex.toString())
                }
            }
        }


    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(VerCasaViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}