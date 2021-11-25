package ni.edu.uca.rentapp.Interfaces.Usuario.edicionperfil

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import ni.edu.uca.rentapp.EntidadesFrontend.usuarioS
import ni.edu.uca.rentapp.R
import ni.edu.uca.rentapp.RentAppAplication
import ni.edu.uca.rentapp.databinding.ActivityArrendadorBinding
import ni.edu.uca.rentapp.databinding.EditarUsuarioFragmentBinding
import ni.edu.uca.rentapp.databinding.NavHeaderArrendatarioyarrendadorBinding
import ni.edu.uca.rentapp.databinding.RegistrarUsuarioFragmentBinding


class Editar_usuario : Fragment() {
    private lateinit var binding: EditarUsuarioFragmentBinding
    var confirmPass: String = ""
    private val viewModelEdit: EditarUsuarioViewModel by activityViewModels{
        EditarUsuarioViewModelFactory(
            (activity?.application as RentAppAplication).database.userDao()
        )
    }



    // This property is only valid between onCreateView and
    // onDestroyView.

    val REQUEST_CAMERA = 1
    var foto : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditarUsuarioFragmentBinding.inflate(inflater, container, false)


        with(binding){
            txtNombre.setText(usuarioS.nombre)
            txtApellido.setText(usuarioS.apellido)
            txtCedula.setText(usuarioS.cedula)
            txtMovil.setText(usuarioS.movil)
            txtCorreoElectronico.setText(usuarioS.correo)
            ivImagen.setImageURI(usuarioS.fotoPerfil.toUri())
        }


        return binding.root
    }



    private fun isEntryValid(): Boolean{
        if(binding.txtPassword.text.toString().equals(binding.txtConfirmarPassword.text.toString())){
            confirmPass = binding.txtConfirmarPassword.text.toString()
        } else {
            Toast.makeText(getActivity(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
        }

        Log.e("INFO isEntryValid:", binding.txtApellido.toString())

        return viewModelEdit.isEntryValid(
            usuarioS.idUsuario,
            binding.txtNombre.text.toString(),
            binding.txtApellido.text.toString(),
            usuarioS.tipoUsuario,
            binding.txtCorreoElectronico.text.toString(),
            binding.txtCedula.text.toString(),
            confirmPass,
            binding.txtMovil.text.toString(),
            foto.toString()
        )

    }

    private fun refactorUser(){
        if(isEntryValid()){
            viewModelEdit.refactorUser(
                usuarioS.idUsuario,
                binding.txtNombre.text.toString(),
                binding.txtApellido.text.toString(),
                usuarioS.tipoUsuario,
                binding.txtCorreoElectronico.text.toString(),
                binding.txtCedula.text.toString(),
                confirmPass,
                binding.txtMovil.text.toString(),
                foto.toString()
            )
            Log.e("INFO refactorUser:", binding.txtApellido.toString())
            Toast.makeText(getActivity(), "Se edito correctamente!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnEditar.setOnClickListener {
            refactorUser()
        }
        binding.btnseleccionarFoto.setOnClickListener(){requestPermissions()}
        binding.btnTomarFoto.setOnClickListener(){pedirPermiso()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    //Creacion de funciones y variables para elegir una imagen del celular
    private fun requestPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            when{
                ContextCompat.checkSelfPermission(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGallery()
                }
                else -> permiso.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }

        }else{
            pickPhotoFromGallery()
        }
    }

    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        starForActivityGallery.launch(intent)
    }

    private val starForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result->
        if (result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data
            binding.ivImagen.setImageURI(data)
        }
    }

    private val permiso = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
        if(isGranted) {
            pickPhotoFromGallery()
        }else{
            Toast.makeText(getActivity(), "Necesitas habilitar los permisos", Toast.LENGTH_LONG).show()
        }
    } //aqui termina lo de seleccionar las imagenes

    //Creacion de variables y funciones para tomar una foto
    private fun pedirPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when{
                ContextCompat.checkSelfPermission(activity!!, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                    dispatchTakePictureIntent()
                }
                else -> damePermi.launch(Manifest.permission.CAMERA)
            }
        }else{
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE,"Nueva Imagen")
        foto = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
        starForActivityCamara.launch(camaraIntent)
    }

    private val starForActivityCamara = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result->
        if (result.resultCode == Activity.RESULT_OK){
            binding.ivImagen.setImageURI(foto)
        }
    }

    private val damePermi = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
        if(isGranted) {
            dispatchTakePictureIntent()
        }else{
            Toast.makeText(getActivity(), "Necesitas habilitar los permisos", Toast.LENGTH_LONG).show()
        }
    }
    //fin de tomar foto
}