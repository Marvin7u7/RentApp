package ni.edu.uca.rentapp.Entidades
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface casasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarCasa(casa: Casa)

    @Query("SELECT * FROM tblCasas")
    fun traerCasas(): LiveData<List<Casa>>
}