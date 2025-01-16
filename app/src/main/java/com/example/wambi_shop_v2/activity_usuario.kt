package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_usuario : AppCompatActivity() {

    // Metodo que se ejecuta cuando se crea la actividad.
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita un diseño de borde a borde para la actividad.
        setContentView(R.layout.activity_usuario) // Establece el layout de la actividad.

        // Configura los márgenes del layout según el sistema de barras del dispositivo.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del botón para ir al carrito.
        val botonCarrito = findViewById<ImageView>(R.id.cartButton)
        botonCarrito.setOnClickListener {
            val intent = Intent(this, activity_carrito::class.java) // Redirige al carrito.
            startActivity(intent)
        }

        // Configuración del botón de inicio.
        val botonHome = findViewById<ImageView>(R.id.homeButton)
        botonHome.setOnClickListener {
            val intent = Intent(this, activity_hallshop::class.java) // Redirige a la tienda.
            startActivity(intent)
        }

        // Configuración del botón de perfil de usuario.
        val botonPerfil = findViewById<ImageView>(R.id.profileButton)
        botonPerfil.setOnClickListener {
            val intent = Intent(this, activity_usuario::class.java) // Redirige a la página de usuario.
            startActivity(intent)
        }

        // Configuración del botón para desconectar.
        val botonDesconectar = findViewById<Button>(R.id.buttonDesconectar)
        botonDesconectar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Redirige a la actividad principal.
            startActivity(intent)
        }
    }
}
