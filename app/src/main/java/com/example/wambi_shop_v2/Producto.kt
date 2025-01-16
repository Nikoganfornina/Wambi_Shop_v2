import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Producto(
    val id: Int?,
    val nombre: String?,
    val categoria: String?,
    val precio: Double?,
    val stock: Int?,
    val descripcion: String?,
    val imagen: String? // Ruta o URL
) : Parcelable

