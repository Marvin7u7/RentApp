package ni.edu.uca.rentapp.Entidades
import androidx.room.*


@Dao
interface usuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarUsuario(user: usuario)

    @Query("UPDATE tblUsuario SET nombre = :nombre, apellido = :apellido, tipoUsuario = :tipoUsuario, email = :correo, cedula = :cedula, password = :password, telefono = :movil, foto = :foto WHERE idUsuario = :id")
    suspend fun editarUsuario(id: Int, nombre: String, apellido: String, tipoUsuario: String, correo: String, cedula: String, password: String, movil: String, foto: String)

    @Query("SELECT * FROM tblUsuario WHERE email LIKE :emailI and password LIKE :passwordI")
    fun loginUsuario(emailI:String, passwordI: String): usuario

    @Query("SELECT * FROM tblUsuario WHERE idUsuario = :id")
    fun propietario(id: Int): usuario
}