package ni.edu.uca.rentapp
import android.app.Application
import ni.edu.uca.rentapp.Entidades.rentAppDataBase

class rentApplication: Application() {
    val database: rentAppDataBase by lazy{rentAppDataBase.getDatabase(this)}
}