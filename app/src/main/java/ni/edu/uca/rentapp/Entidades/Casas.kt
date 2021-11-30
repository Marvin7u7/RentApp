package ni.edu.uca.rentapp.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblCasas")
data class Casa(
    @PrimaryKey(autoGenerate = true)
    val idCasa: Int = 0,
    @ColumnInfo(name = "pisos")
    val pisos: String,
    @ColumnInfo(name = "cuartos")
    val cuartos: String,
    @ColumnInfo(name = "baños")
    val baños: String,
    @ColumnInfo(name = "precioMes")
    val precioMes: String,
    @ColumnInfo(name = "descripcionG")
    val descripcionG: String,
    @ColumnInfo(name = "descripcionB")
    val descripcionB: String,
    @ColumnInfo(name = "departamento")
    val departamento: Int,
    @ColumnInfo(name = "foto")
    val foto: String,
    @ColumnInfo(name = "propietario")
    val propietario: Int
)