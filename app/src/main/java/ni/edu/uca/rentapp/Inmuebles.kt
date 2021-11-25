package ni.edu.uca.rentapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.rentapp.Entidades.departamentos
import ni.edu.uca.rentapp.databinding.InmueblesFragmentBinding
import ni.edu.uca.rentapp.databinding.LoginFragmentBinding
import okhttp3.Dispatcher

class Inmuebles : Fragment() {

    private val ViewModel: InmueblesViewModel by activityViewModels{
        ImueblesViewModelFactory(
            (activity?.application as RentAppAplication).database.depsDao()
        )
    }

    companion object {
        fun newInstance() = Inmuebles()
    }


    private lateinit var binding: InmueblesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InmueblesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun mensaje(){
        lifecycleScope.launch(){
            withContext(Dispatchers.IO){
                try {
                    val lista: List<String> = ViewModel.fachada()
                    if(lista.size > 0){
                        lifecycleScope.launch(){
                            withContext(Dispatchers.Main){
                                val adaptador: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, lista)
                                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                binding.spinnerDeps.adapter = adaptador
                            }
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error, mejor matate.", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: Exception){
                    Log.e("Error", e.toString())
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mensaje()
    }

}
