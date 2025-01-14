package ni.edu.uca.rentapp

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.databinding.LoginFragmentBinding
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.rentapp.EntidadesFrontend.usuarioS
import java.lang.Exception
import kotlin.math.sign


class Login : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels{
        LoginViewModelFactory(
            (activity?.application as RentAppAplication).database.userDao(),
            (activity?.application as RentAppAplication).database.depsDao()
        )
    }
    private lateinit var binding: LoginFragmentBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)*/

        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean{
        return loginViewModel.isEntryValid(
            binding.txtCorreoElectronico.text.toString(),
            binding.txtPassword.text.toString(),
        )
    }

    fun insertarDepas(){
        lifecycleScope.launch(){
            withContext(Dispatchers.IO){
                Log.e("ERROR", "insertarDepas")
                try {
                    var lista = loginViewModel.bringDeps()
                    if(lista == 0){
                        Log.e("ERROR", "insertarDepasCondicional")
                        loginViewModel.crearDepsFront()
                    }
                }catch (ex: Exception){
                    Log.e("ERROR", ex.toString())
                }
            }
        }
    }


    private fun validar(){
        var signal: Boolean = false
        lifecycleScope.launch{
             withContext(Dispatchers.IO){
                try{
                    if(isEntryValid()){
                        val objeto = loginViewModel.searchUser(binding.txtCorreoElectronico.text.toString(), binding.txtPassword.text.toString())
                        if(objeto.email.equals(binding.txtCorreoElectronico.text.toString()) && objeto.password.equals(binding.txtPassword.text.toString())){
                            if(objeto.tipoUsuario.equals("Arrendador")){
                                lifecycleScope.launch(){
                                    withContext(Dispatchers.Main){
                                        //Asignando valores a entidad frontend
                                        usuarioS.idUsuario = objeto.idUsuario
                                        usuarioS.nombre = objeto.nombre
                                        usuarioS.apellido = objeto.apellido
                                        usuarioS.cedula = objeto.cedula
                                        usuarioS.movil = objeto.telefono
                                        usuarioS.correo = objeto.email
                                        usuarioS.fotoPerfil = objeto.foto
                                        usuarioS.tipoUsuario = objeto.tipoUsuario
                                        //Iniciando otro activity
                                        val intent = Intent(activity, Arrendador()::class.java)
                                        startActivity(intent)
                                    }
                                }
                            }else if(objeto.tipoUsuario.equals("Arrendatario")) {
                                lifecycleScope.launch(){
                                    withContext(Dispatchers.Main){
                                        //Asignando valores a entidad frontend
                                        usuarioS.idUsuario = objeto.idUsuario
                                        usuarioS.nombre = objeto.nombre
                                        usuarioS.apellido = objeto.apellido
                                        usuarioS.cedula = objeto.cedula
                                        usuarioS.movil = objeto.telefono
                                        usuarioS.correo = objeto.email
                                        usuarioS.fotoPerfil = objeto.foto
                                        usuarioS.tipoUsuario = objeto.tipoUsuario
                                        val intent = Intent(activity, Marvin()::class.java)
                                        startActivity(intent)
                                    }
                                }
                            }
                            //Log.e("SIGNAL: ", signal.toString())
                            //Log.e("EMAIL: ", objeto.email)
                            //Log.e("PASSWORD: ", objeto.password)
                        } else {
                            Toast.makeText(getActivity(), "La contraseña o correo electronico son incorrectos", Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch(e: Exception){
                    Log.e("Error", e.toString())
                }
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        insertarDepas()

        binding.btnIniciarSesion.setOnClickListener(){
            validar()
        }
    }

}