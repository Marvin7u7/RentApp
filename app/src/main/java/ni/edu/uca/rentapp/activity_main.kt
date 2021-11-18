package ni.edu.uca.rentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.Interfaces.Usuario.UsuarioViewModel
import ni.edu.uca.rentapp.databinding.FragmentActivityMainBinding
import ni.edu.uca.rentapp.databinding.FragmentUsuarioBinding

class activity_main : Fragment() {
    private lateinit var activityMain: ActivityMainViewModel
    private var _binding: FragmentActivityMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activityMain =
            ViewModelProvider(this).get(ActivityMainViewModel::class.java)

        _binding = FragmentActivityMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}