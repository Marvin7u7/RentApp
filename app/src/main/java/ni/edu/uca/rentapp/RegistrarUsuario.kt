package ni.edu.uca.rentapp

import android.Manifest
import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ni.edu.uca.rentapp.Entidades.usuario
import ni.edu.uca.rentapp.EntidadesFrontend.usuarioS
import ni.edu.uca.rentapp.databinding.RegistrarUsuarioFragmentBinding
import android.R.attr.data
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.ImageReader
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.rentapp.EntidadesFrontend.motorConversion


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

    val REQUEST_CAMERA = 1
    var foto : Uri? = null

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
            binding.txtTelefono.text.toString(),
            foto.toString()
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
                binding.txtTelefono.text.toString(),
                foto.toString()
            )
        }
    }

    //Creacion de funciones y variables para elegir una imagen del celular
    private fun requestPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            when{
                ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
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
        if (result.resultCode == RESULT_OK){
            val data = result.data?.data
            binding.imgFotoPerfil.setImageURI(data)
        }
    }

    private val permiso = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
        if(isGranted) {
            pickPhotoFromGallery()
        }else{
            Toast.makeText(getActivity(), "Necesitas habilitar los permisos",Toast.LENGTH_LONG).show()
        }
    } //aqui termina lo de seleccionar las imagenes

    //Creacion de variables y funciones para tomar una foto
    private fun pedirPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when{
                ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
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
        foto = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
        starForActivityCamara.launch(camaraIntent)
    }

    private val starForActivityCamara = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result->
        if (result.resultCode == RESULT_OK){
            binding.imgFotoPerfil.setImageURI(foto)
        }
    }

    private val damePermi = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
        if(isGranted) {
            dispatchTakePictureIntent()
        }else{
            Toast.makeText(getActivity(), "Necesitas habilitar los permisos",Toast.LENGTH_LONG).show()
        }
    }
    //fin de tomar foto

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnGuardarUsuario.setOnClickListener {

            addNewUser()
            Toast.makeText(getActivity(), "Se ah registrado correctamete", Toast.LENGTH_LONG).show()
        }
        binding.btnSeleccionarFoto.setOnClickListener(){requestPermissions()}
        binding.btnTomarFoto.setOnClickListener(){pedirPermiso()}
    }

}