package ni.edu.uca.rentapp.Entidades

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ni.edu.uca.rentapp.EntidadesFrontend.motorConversion

@Database(entities = [usuario::class, departamentos::class, Casa::class], version = 8, exportSchema = false)
@TypeConverters(motorConversion::class)
abstract class appRoomDatabase: RoomDatabase() {
    abstract fun userDao(): usuarioDao
    abstract fun depsDao(): departamentoDao
    abstract fun casaDao(): casasDao
    companion object {
        @Volatile
        private var INSTANCE: appRoomDatabase? = null
        fun getDatabase(context: Context): appRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    appRoomDatabase::class.java,
                    "dbRentApp").fallbackToDestructiveMigration().build()
                return instance
            }
        }
    }
}