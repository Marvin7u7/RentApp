package ni.edu.uca.rentapp.Entidades

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface departamentoDao {
    @Query("SELECT nombre FROM tblDepartamentos")
    fun seleccionarDepartamentos(): List<String>

    @Query("SELECT COUNT(nombre) FROM tblDepartamentos")
    fun verificarTabla(): List<String>

    @Query("INSERT INTO tblDepartamentos(nombre) VALUES ( 'Chinandega'),( 'Chontales'), ('Esteli'), ('Granada'), ('Jinotega'),( 'León'), ('Madriz'), ('Managua'), ('Masaya'),( 'Matagalpa'), ('Nueva Segovia'), ('Río San Juan'), ('Rivas')")
    fun crearDepas()
}