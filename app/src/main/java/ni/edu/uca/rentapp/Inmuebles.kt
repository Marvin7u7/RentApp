package ni.edu.uca.rentapp

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.rentapp.Entidades.departamentos
import ni.edu.uca.rentapp.Entidades.usuarioDao
import ni.edu.uca.rentapp.EntidadesFrontend.usuarioS
import ni.edu.uca.rentapp.databinding.InmueblesFragmentBinding
import ni.edu.uca.rentapp.databinding.LoginFragmentBinding
import okhttp3.Dispatcher

class Inmuebles : Fragment() {
    var pisos: String = ""
    var locacion: Int = 0
    var listaPisos: List<String> = listOf("1", "2", "3", "4")
    lateinit var lista: List<String>
    val REQUEST_CAMERA = 1
    var foto : Uri? = null


    private val ViewModel: InmueblesViewModel by activityViewModels{
        ImueblesViewModelFactory(
            (activity?.application as RentAppAplication).database.depsDao(),
            (activity?.application as RentAppAplication).database.casaDao()
        )
    }

    companion object {
        fun newInstance() = Inmuebles()
    }


    private lateinit var binding: InmueblesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InmueblesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun mensaje(){
        lifecycleScope.launch(){
            withContext(Dispatchers.IO){
                try {
                    lista = ViewModel.fachada()
                    if(lista.size > 0){
                        lifecycleScope.launch(){
                            withContext(Dispatchers.Main){
                                val adaptador: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, lista)
                                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                binding.spinnerDeps.adapter = adaptador
                            }
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error, mejor matate.", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: Exception){
                    Log.e("Error", e.toString())
                }
            }
        }
    }

    fun llenarSpinnerPisos(){
        val adaptador: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, listaPisos)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPisos.adapter = adaptador
    }

    private fun isEntryValid(): Boolean{

        Log.e("isEntryValidFront", binding.txtPriceMonth.text.toString())
        return ViewModel.isEntryValid(
            pisos,
            binding.txtCuartos.text.toString(),
            binding.txtBath.text.toString(),
            binding.txtPriceMonth.text.toString(),
            binding.txtDescripG.text.toString(),
            binding.txtDescripB.text.toString(),
            locacion,
            foto.toString(),
            usuarioS.idUsuario
        )
    }

    private fun addNewHouse(){
        if(isEntryValid()){
            ViewModel.addNewHouse(
                pisos,
                binding.txtCuartos.text.toString(),
                binding.txtBath.text.toString(),
                binding.txtPriceMonth.text.toString(),
                binding.txtDescripG.text.toString(),
                binding.txtDescripB.text.toString(),
                locacion,
                foto.toString(),
                usuarioS.idUsuario
            )
            Log.e("addNewHouseFront", binding.txtPriceMonth.toString())
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
        if (result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data
            binding.ivFotoCasa.setImageURI(data)
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
        if (result.resultCode == Activity.RESULT_OK){
            binding.ivFotoCasa.setImageURI(foto)
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
        binding.spinnerPisos.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pisos = listaPisos[position]
                //Toast.makeText(getActivity(), "$pisos", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerDeps.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                locacion = position + 1
                //Toast.makeText(getActivity(), "${lista[locacion]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }



        llenarSpinnerPisos()
        mensaje()
        binding.btnGuardar.setOnClickListener(){
            try{
                addNewHouse()
            }catch (ex: Exception){
                Log.e("ERRORRR!", ex.toString())
            }
        }

        binding.btnCargarFoto.setOnClickListener(){requestPermissions()}
        binding.btnAgregarFotos.setOnClickListener(){pedirPermiso()}
    }

}
