package ni.edu.uca.rentapp.Entidades

import android.content.Context
import androidx.room.*
import ni.edu.uca.rentapp.EntidadesFrontend.motorConversion

@Database(entities = [usuario::class], version = 4, exportSchema = false)
@TypeConverters(motorConversion::class)
abstract class appRoomDatabase: RoomDatabase() {
    abstract fun userDao(): usuarioDao
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