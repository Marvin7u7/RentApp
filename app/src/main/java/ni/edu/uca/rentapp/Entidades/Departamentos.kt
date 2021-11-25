package ni.edu.uca.rentapp.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblDepartamentos")
data class departamentos(
    @PrimaryKey(autoGenerate = true)
    val idDepartamento: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombreDep: String
)