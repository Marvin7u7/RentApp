package ni.edu.uca.rentapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.rentapp.databinding.ActivityIniciarSesionBinding

class Iniciar_sesion : AppCompatActivity() {
    private lateinit var binding : ActivityIniciarSesionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciarSesion.setOnClickListener(){
            val intent = Intent(this,Marvin::class.java)
            startActivity(intent)
        }
    }
}