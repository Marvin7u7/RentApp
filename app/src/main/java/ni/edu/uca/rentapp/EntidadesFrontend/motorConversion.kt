package ni.edu.uca.rentapp.EntidadesFrontend

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class motorConversion {

    @TypeConverter
    fun fromBitmapToByteLong(bmp: Bitmap): ByteArray {
        val salida = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, salida)
        return salida.toByteArray()
    }

    @TypeConverter
    fun toBitmap(ba: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(ba, 0, ba.size)
    }
}