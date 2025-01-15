package com.example.wambi_shop_v2

import ProductosAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity_hallshop : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hallshop)

        // Cargar productos desde la base de datos o archivo

        val productos = Database.DBHelper(this).getProductInfo()

        // Configurar RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycler_productos)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        var adapter = ProductosAdapter(productos) { producto ->
            Toast.makeText(this, "Producto clicado: ${producto.nombre}", Toast.LENGTH_SHORT).show()

        }

        recyclerView.adapter = adapter

        val categoriesBBDD = arrayOf(
            "dulces",
            "fruta y verduras",
            "utensilios",
            "platos",
            "congelados",
            "fideos"
        )
        val categories = arrayOf(
            findViewById<LinearLayout>(R.id.category_dulces_postres),
            findViewById<LinearLayout>(R.id.category_frutas_verduras),
            findViewById<LinearLayout>(R.id.category_utensilios),
            findViewById<LinearLayout>(R.id.category_platos_combinados),
            findViewById<LinearLayout>(R.id.category_congelados),
            findViewById<LinearLayout>(R.id.category_pastas_fideos)
        )
        val db = Database.DBHelper(this)
        for(i in 0 until categories.size) {
            categories[i].setOnClickListener {
                adapter.actualizarProductos(db.getProductInfoCategoria(categoriesBBDD[i]),layoutManager)
            }
        }

        // Buscar producto

        val buscarProductoEditText = findViewById<EditText>(R.id.buscarProducto)
        val botonBuscarImageView = findViewById<ImageView>(R.id.BotonBuscar)

        botonBuscarImageView.setOnClickListener {
            val query = buscarProductoEditText.text.toString()

            if (query.isNotEmpty()) {
                // Realizamos la búsqueda fonética con el nombre ingresado
                val productos = db.searchProductByNameLike(query)

                if (productos.isNotEmpty()) {
                    // Si encontramos productos, actualizamos el RecyclerView con los productos encontrados
                    adapter.actualizarProductos(productos, layoutManager)
                } else {
                    // Si no se encontraron productos, mostramos un mensaje
                    Toast.makeText(this, "No se encontró el producto", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Si el campo de búsqueda está vacío, también puedes mostrar un mensaje
                adapter.actualizarProductos(db.getProductInfo(),layoutManager)
                Toast.makeText(this, "Se han restablecido los productos", Toast.LENGTH_SHORT).show()
            }
        }




        val botonHome = findViewById<ImageView>(R.id.homeButton)
        botonHome.setOnClickListener {

            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

        }
        val botonCarrito = findViewById<ImageView>(R.id.cartButton)
        botonCarrito.setOnClickListener {

            val intent = Intent(this, activity_carrito::class.java)
            startActivity(intent)

        }

        val botonPerfil = findViewById<ImageView>(R.id.profileButton)
        botonPerfil.setOnClickListener {

            val intent = Intent(this, activity_usuario::class.java)
            startActivity(intent)

        }
    }
}