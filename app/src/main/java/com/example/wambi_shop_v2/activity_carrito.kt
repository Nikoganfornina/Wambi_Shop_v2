package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity_carrito : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnConfirmarCompra: Button
    private lateinit var carritoAdapter: CarritoAdapter

    // Para efectos de simulación, asumimos que el usuario logueado tiene ID 1
    private val userId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrito)

        // Ajuste para sistemas con insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración de navegación
        findViewById<ImageView>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, activity_hallshop::class.java))
        }
        findViewById<ImageView>(R.id.cartButton).setOnClickListener {
            cargarCarrito() // Refrescar la lista
        }
        findViewById<ImageView>(R.id.profileButton).setOnClickListener {
            startActivity(Intent(this, activity_usuario::class.java))
        }

        recyclerView = findViewById(R.id.recyclerViewCarrito)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        btnConfirmarCompra = findViewById(R.id.botonComprar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        carritoAdapter = CarritoAdapter(mutableListOf(), onIncrement = { cartItem ->
            // Incrementar cantidad en la base de datos
            Database().addProductoAlCarrito(this, cartItem.producto.id ?: 0, userId, 1)
            cargarCarrito()
        }, onDecrement = { cartItem ->
            if (cartItem.cantidad > 1) {
                updateCantidadEnCarrito(cartItem.idCarrito, cartItem.cantidad - 1)
            } else {
                removeCartItem(cartItem.idCarrito)
            }
            cargarCarrito()
        })
        recyclerView.adapter = carritoAdapter

        cargarCarrito()

        btnConfirmarCompra.setOnClickListener {
            val carritoItems = Database().getCarritoItems(this, userId)
            val intent = Intent(this, activity_confirmarcarrito::class.java)
            intent.putParcelableArrayListExtra("carritoItems", ArrayList(carritoItems))
            startActivity(intent)
        }
    }

    private fun cargarCarrito() {
        val carritoItems = Database().getCarritoItems(this, userId)
        carritoAdapter.updateItems(carritoItems)
        calcularTotal(carritoItems)
    }

    private fun calcularTotal(carritoItems: MutableList<CartItem>) {
        var total = 0.0
        carritoItems.forEach { item ->
            total += (item.producto.precio ?: 0.0) * item.cantidad
        }
        tvTotalPrice.text = "Total: $total €"
    }

    private fun updateCantidadEnCarrito(idCarrito: Int, nuevaCantidad: Int) {
        val dbHelper = Database.DBHelper(this)
        val db = dbHelper.writableDatabase
        val contentValues = android.content.ContentValues().apply {
            put("cantidad", nuevaCantidad)
        }
        db.update(Database.TABLE_CARRITO, contentValues, "id_carrito = ?", arrayOf(idCarrito.toString()))
        db.close()
    }

    private fun removeCartItem(idCarrito: Int) {
        val dbHelper = Database.DBHelper(this)
        val db = dbHelper.writableDatabase
        db.delete(Database.TABLE_CARRITO, "id_carrito = ?", arrayOf(idCarrito.toString()))
        db.close()
    }
}
