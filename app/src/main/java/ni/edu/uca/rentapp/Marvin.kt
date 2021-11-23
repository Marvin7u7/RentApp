package ni.edu.uca.rentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.net.toUri
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.*
import ni.edu.uca.rentapp.EntidadesFrontend.usuarioS
import ni.edu.uca.rentapp.R.id.nav_host_fragment_content_arrendatario
import ni.edu.uca.rentapp.databinding.ActivityMarvinBinding
import ni.edu.uca.rentapp.databinding.NavHeaderArrendatarioyarrendadorBinding

class Marvin : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMarvinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarvinBinding.inflate(layoutInflater)
        setContentView(binding.root)

     //  setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val profileView = binding.navView.getHeaderView(0)
        val headerBinding = NavHeaderArrendatarioyarrendadorBinding.bind(profileView)
        val navController = findNavController(nav_host_fragment_content_arrendatario)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_Inicio, R.id.nav_buscar_casa, R.id.nav_arrendatario, R.id.nav_usuario
            ), drawerLayout
        )

        headerBinding.ivFotoPerfil.setImageURI(usuarioS.fotoPerfil.toUri())
        headerBinding.tvNombreUsuario.text = usuarioS.nombre
        headerBinding.tvCorreo.text = usuarioS.correo

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    //Menu 3 puntos
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_arrendatario)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    //abrir fragment para el menu 3 puntos
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(nav_host_fragment_content_arrendatario)
        when(item.itemId){
            R.id.nav_editar_usuario -> item.onNavDestinationSelected(navController)
        }
        return super.onOptionsItemSelected(item)
    }
}

