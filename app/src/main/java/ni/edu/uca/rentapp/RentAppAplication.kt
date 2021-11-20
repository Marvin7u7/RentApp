package ni.edu.uca.rentapp

import android.app.Application
import ni.edu.uca.rentapp.Entidades.appRoomDatabase

class RentAppAplication: Application() {
    val database: appRoomDatabase by lazy {
        appRoomDatabase.getDatabase(this)
    }
}