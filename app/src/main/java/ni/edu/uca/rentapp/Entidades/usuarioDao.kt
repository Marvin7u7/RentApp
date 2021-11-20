package ni.edu.uca.rentapp.Entidades
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface usuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuario(user: usuario)

    @Query("SELECT * FROM tblUsuario WHERE email LIKE :emailI and password LIKE :passwordI")
    fun loginUsuario(emailI:String, passwordI: String): usuario
}