package ni.edu.uca.rentapp.Entidades
import androidx.room.*

@Dao
interface casasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarCasa(casa: casas)
}