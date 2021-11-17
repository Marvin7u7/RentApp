package ni.edu.uca.rentapp.Entidades
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface usuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(usuario: Usuario)

    @Update
    suspend fun updateUser(usuario: Usuario)


}