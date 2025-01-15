import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wambi_shop_v2.R

class ProductosAdapter(private var productos: List<Producto> , private val onProductClick: (Producto) -> Unit)  : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombreProducto)
        val precio: TextView = view.findViewById(R.id.precioProducto)
        val imagen: ImageView = view.findViewById(R.id.imagenProducto)
        //val verProducto: Button = view.findViewById(R.id.verProductoButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = "${producto.precio} €"
        val resId = holder.itemView.context.resources.getIdentifier(producto.imagen, "drawable", holder.itemView.context.packageName)
        if (resId != 0) {
            holder.imagen.setImageResource(resId) // Establecer la imagen desde los recursos
        } else {
            // Si no se encuentra la imagen, puedes poner una imagen por defecto
            holder.imagen.setImageResource(R.drawable.ic_error) // Asegúrate de tener una imagen predeterminada
        }


        // Asume que `imagen` es una URL local o remota; usa Glide o Picasso para cargarla
        //holder.verProducto.setOnClickListener { onVerProductoClick(producto) }
        // Agregar clic en la tarjeta completa
        holder.itemView.setOnClickListener {
            onProductClick(producto) // Llama al callback con el producto seleccionado
        }

    }
        override fun getItemCount(): Int = productos.size

    // Metodo para actualizar los productos y notificar al adaptador
    fun actualizarProductos(nuevosProductos: List<Producto>, layoutManager: RecyclerView.LayoutManager) {
        this.productos = nuevosProductos
        notifyDataSetChanged() // Notificar que los datos han cambiado
        layoutManager.scrollToPosition(0)
    }
}
