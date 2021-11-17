package ni.edu.uca.rentapp.Entidades
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class rentAppDataBase: RoomDatabase() {
    abstract fun userDao(): usuarioDao

    companion object {
        @Volatile
        private var INSTANCE: rentAppDataBase? = null
        fun getDatabase(context: Context): rentAppDataBase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    rentAppDataBase::class.java,
                    "dbrentapp").fallbackToDestructiveMigration().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}