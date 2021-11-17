package ni.edu.uca.rentapp.Entidades
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "apellido")
    val apellido: String,
    @ColumnInfo(name = "tipoUsuario")
    val tipoUsuario: Int = 0,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "cedula")
    val numeroCedula: String,
    @ColumnInfo(name = "pwd")
    val contraseña: String,
    @ColumnInfo(name = "movil")
    val numeroTelefono: String
) {

}