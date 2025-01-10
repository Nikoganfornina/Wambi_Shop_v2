package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class activity_hallshop : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hallshop)

        val botonCarrito = findViewById<ImageView>(R.id.cartButton)
        botonCarrito.setOnClickListener {

            val intent = Intent(this, activity_carrito::class.java)
            startActivity(intent)

        }

        val botonHome = findViewById<ImageView>(R.id.homeButton)
        botonHome.setOnClickListener {

            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

        }
        val botonPerfil = findViewById<ImageView>(R.id.profileButton)
        botonPerfil.setOnClickListener {

            val intent = Intent(this, activity_usuario::class.java)
            startActivity(intent)

        }
    }


}