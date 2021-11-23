package ni.edu.uca.rentapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.google.android.material.navigation.NavigationView
import ni.edu.uca.rentapp.EntidadesFrontend.usuarioS
import ni.edu.uca.rentapp.databinding.ActivityMarvinBinding
import ni.edu.uca.rentapp.databinding.MiPerfilFragmentBinding
import ni.edu.uca.rentapp.databinding.NavHeaderArrendatarioyarrendadorBinding
import ni.edu.uca.rentapp.databinding.RegistrarUsuarioFragmentBinding

class MiPerfil : Fragment() {
    private lateinit var viewModel: MiPerfilViewModel
    private lateinit var binding: MiPerfilFragmentBinding


    companion object {
        fun newInstance() = MiPerfil()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MiPerfilFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MiPerfilViewModel::class.java)
        binding.imgPFP.setImageURI(usuarioS.fotoPerfil.toUri())
        binding.tvNombrePH.text = usuarioS.nombre
        binding.tvApellidoPH.text = usuarioS.apellido
        binding.tvEmailPH.text = usuarioS.correo
        binding.tvMovilPH.text = usuarioS.movil
        binding.tvCedulaPH.text = usuarioS.cedula
    }

}