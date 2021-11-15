package ni.edu.uca.rentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ni.edu.uca.rentapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnIniciarSesion.setOnClickListener(){
            val intent = Intent(this,Iniciar_sesion::class.java)
            startActivity(intent)
        }

        binding.btnRegistrar.setOnClickListener(){
            val intent = Intent(this, Registrar::class.java)
            startActivity(intent)
        }

       }
}