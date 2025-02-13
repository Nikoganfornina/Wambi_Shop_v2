package com.example.wambi_shop_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wambi_shop_v2.R

class CarritoAdapter(
    private var carritoItems: MutableList<CartItem>,
    private val onIncrement: (CartItem) -> Unit,
    private val onDecrement: (CartItem) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.productImageCart)
        val productName: TextView = view.findViewById(R.id.productNameCart)
        val productPrice: TextView = view.findViewById(R.id.productPriceCart)
        val productQuantity: TextView = view.findViewById(R.id.productQuantityCart)
        val btnIncrement: Button = view.findViewById(R.id.btnIncrement)
        val btnDecrement: Button = view.findViewById(R.id.btnDecrement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val cartItem = carritoItems[position]
        holder.productName.text = cartItem.producto.nombre
        holder.productPrice.text = "${cartItem.producto.precio} €"
        holder.productQuantity.text = cartItem.cantidad.toString()

        val context = holder.itemView.context
        val resId = context.resources.getIdentifier(cartItem.producto.imagen, "drawable", context.packageName)
        if (resId != 0) {
            holder.productImage.setImageResource(resId)
        } else {
            holder.productImage.setImageResource(R.drawable.ic_error)
        }

        holder.btnIncrement.setOnClickListener {
            onIncrement(cartItem)
        }
        holder.btnDecrement.setOnClickListener {
            onDecrement(cartItem)
        }
    }

    override fun getItemCount(): Int = carritoItems.size

    fun updateItems(newItems: MutableList<CartItem>) {
        carritoItems = newItems
        notifyDataSetChanged()
    }
}
