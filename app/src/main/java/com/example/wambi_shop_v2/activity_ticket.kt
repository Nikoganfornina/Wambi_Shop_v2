package com.example.wambi_shop_v2

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class activity_ticket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        // Recuperamos los datos enviados desde la activity_confirmarcarrito
        val userName = intent.getStringExtra("userName") ?: "Cliente"
        val carritoItems = intent.getParcelableArrayListExtra<CartItem>("carritoItems")
        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        val metodoPago = intent.getStringExtra("metodoPago") ?: ""

        // Referencias a los elementos de la factura
        val tvHeader = findViewById<TextView>(R.id.tvHeader)
        val tvSubHeader = findViewById<TextView>(R.id.tvSubHeader)
        val tvClientName = findViewById<TextView>(R.id.tvClientName)
        val llInvoiceItems = findViewById<LinearLayout>(R.id.llInvoiceItems)
        val tvTotalInvoice = findViewById<TextView>(R.id.tvTotalInvoice)
        val tvPaymentMethod = findViewById<TextView>(R.id.tvPaymentMethod)
        val tvFooter = findViewById<TextView>(R.id.tvFooter)

        // Configuramos textos fijos
        tvHeader.text = "WAMBI SHOP"
        tvSubHeader.text = "Creado por Carla, Niko y Lucas"
        tvClientName.text = "Cliente: $userName"
        tvPaymentMethod.text = "Método de Pago: $metodoPago"

        // Vaciamos el contenedor (por si tiene vistas previas)
        llInvoiceItems.removeAllViews()

        // Por cada ítem comprado, inflamos el layout item_factura.xml y lo añadimos al contenedor
        carritoItems?.forEach { item ->
            val itemView = LayoutInflater.from(this).inflate(R.layout.item_factura, llInvoiceItems, false)
            val tvFacturaCantidad = itemView.findViewById<TextView>(R.id.tvFacturaCantidad)
            val tvFacturaNombre = itemView.findViewById<TextView>(R.id.tvFacturaNombre)
            val tvFacturaPrecio = itemView.findViewById<TextView>(R.id.tvFacturaPrecio)

            tvFacturaCantidad.text = "${item.cantidad} x"
            tvFacturaNombre.text = item.producto.nombre ?: "Producto"
            val itemTotal = (item.producto.precio ?: 0.0) * item.cantidad
            tvFacturaPrecio.text = String.format("%.2f €", itemTotal)

            llInvoiceItems.addView(itemView)
        }

        // Configuramos el resumen total y el mensaje final
        tvTotalInvoice.text = "Total: " + String.format("%.2f €", totalPrice)
        tvFooter.text = "GRACIAS POR SU COMPRA, LE ESPERAMOS PRONTO"
    }
}
