package ni.edu.uca.rentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ni.edu.uca.rentapp.databinding.ActivityRegistrarBinding

class Registrar : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnGuardarUsuario.setOnClickListener(){
            Toast.makeText(this, "Se ha gaurdado su información, ya puede iniciar sesión", Toast.LENGTH_LONG).show()
        }
    }
}