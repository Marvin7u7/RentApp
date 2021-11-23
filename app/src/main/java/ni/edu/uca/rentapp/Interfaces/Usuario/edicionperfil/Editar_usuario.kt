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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.rentapp.databinding.EditarUsuarioFragmentBinding

class Editar_usuario : Fragment() {

    private lateinit var editarusuarioViewModel: EditarUsuarioViewModel
    private var _binding: EditarUsuarioFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val REQUEST_CAMERA = 1
    var foto : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editarusuarioViewModel =
            ViewModelProvider(this).get(EditarUsuarioViewModel::class.java)

        _binding = EditarUsuarioFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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