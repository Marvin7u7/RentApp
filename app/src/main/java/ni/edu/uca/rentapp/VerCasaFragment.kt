package ni.edu.uca.rentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.rentapp.Entidades.Casa
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
        //val clickedCasa = viewModel.getCasaById(argument.casaId)

        lifecycleScope.launch(){
            withContext(Dispatchers.IO){
                var clickCasa = viewModel.getCasaById(argument.casaId)
                try {
                    if(clickCasa.idCasa != 0){
                        lifecycleScope.launch(){
                            withContext(Dispatchers.Main){
                                with(binding) {
                                    tvDescripcion.text = clickCasa.descripcionB
                                }
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