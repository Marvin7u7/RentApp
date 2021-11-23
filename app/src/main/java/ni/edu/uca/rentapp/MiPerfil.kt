package ni.edu.uca.rentapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.navigation.NavigationView
import ni.edu.uca.rentapp.databinding.ActivityMarvinBinding
import ni.edu.uca.rentapp.databinding.NavHeaderArrendatarioyarrendadorBinding

class MiPerfil : Fragment() {

    companion object {
        fun newInstance() = MiPerfil()
    }

    private lateinit var viewModel: MiPerfilViewModel
    private lateinit var binding: MiPerfil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mi_perfil_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MiPerfilViewModel::class.java)
    }

}