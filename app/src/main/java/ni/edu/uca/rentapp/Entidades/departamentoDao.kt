package ni.edu.uca.rentapp.Entidades

import androidx.room.Dao
import androidx.room.Query


@Dao
interface departamentoDao {
    @Query("SELECT nombre FROM tblDepartamentos")
    fun seleccionarDepartamentos(): List<String>
}