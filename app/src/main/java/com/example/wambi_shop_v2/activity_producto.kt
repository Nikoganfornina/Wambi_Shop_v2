package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_producto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonAgregarCarrito = findViewById<Button>(R.id.btnAgregarCarrito)
        botonAgregarCarrito.setOnClickListener {

            val intent = Intent(this, activity_carrito::class.java)
            startActivity(intent)

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