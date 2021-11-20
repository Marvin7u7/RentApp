package ni.edu.uca.rentapp.Entidades

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [usuario::class], version = 2, exportSchema = false)
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