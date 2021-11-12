package ni.edu.uca.rentapp.Interfaces.Arrendatario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.databinding.FragmentArrendatarioBinding


class ArrendatarioFragment : Fragment() {

    private lateinit var arrendatarioViewModel: ArrendatarioViewModel
    private var _binding: FragmentArrendatarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arrendatarioViewModel =
            ViewModelProvider(this).get(ArrendatarioViewModel::class.java)

        _binding = FragmentArrendatarioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textArrendatario
        arrendatarioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}