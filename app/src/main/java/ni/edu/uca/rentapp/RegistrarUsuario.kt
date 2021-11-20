package ni.edu.uca.rentapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.databinding.RegistrarUsuarioFragmentBinding

class RegistrarUsuario : Fragment() {
    private lateinit var binding: RegistrarUsuarioFragmentBinding
    var tipoU: String = ""
    var confirmPass: String = ""
    companion object {
        fun newInstance() = RegistrarUsuario()
    }

    private val viewModel: RegistrarUsuarioViewModel by activityViewModels{
        RegistrarUsuarioViewModelFactory(
            (activity?.application as RentAppAplication).database.userDao()
        )
    }

    lateinit var user: usuario

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrarUsuarioFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean{
        if(binding.rbArrendador.isChecked){
            tipoU = "Arrendador"
        }

        if(binding.rbArrendatario.isChecked){
            tipoU = "Arrendatario"
        }

        if(binding.txtPassword.text.toString().equals(binding.txtConfirmarPassword.text.toString())){
            confirmPass = binding.txtPassword.text.toString()
        } else {
            Toast.makeText(getActivity(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
        }

        return viewModel.isEntryValid(
            binding.txtNombre.text.toString(),
            binding.txtApellido.text.toString(),
            tipoU,
            binding.txtEmail.text.toString(),
            binding.txtNumeroCedula.text.toString(),
            confirmPass,
            binding.txtTelefono.text.toString()
        )
    }

    private fun addNewUser(){
        if(isEntryValid()){
            viewModel.addNewUser(
                binding.txtNombre.text.toString(),
                binding.txtApellido.text.toString(),
                tipoU,
                binding.txtEmail.text.toString(),
                binding.txtNumeroCedula.text.toString(),
                confirmPass,
                binding.txtTelefono.text.toString()
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnGuardarUsuario.setOnClickListener {
            addNewUser()
        }
    }

}