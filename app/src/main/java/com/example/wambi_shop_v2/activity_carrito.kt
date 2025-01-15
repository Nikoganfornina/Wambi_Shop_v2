package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Actividad que maneja la vista del carrito de compras.
class activity_carrito : AppCompatActivity() {

    // Metodo que se ejecuta cuando se crea la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita un diseño de borde a borde para la actividad.
        setContentView(R.layout.activity_carrito) // Establece el layout de la actividad.

        // Ajusta el diseño para que se adapte a las barras del sistema (notificaciones, navegación, etc.).
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura la acción del botón para ir a la pantalla principal.
        val botonHome = findViewById<ImageView>(R.id.homeButton)
        botonHome.setOnClickListener {
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)
        }

        // Configura la acción del botón para volver al carrito.
        val botonCarrito = findViewById<ImageView>(R.id.cartButton)
        botonCarrito.setOnClickListener {
            val intent = Intent(this, activity_carrito::class.java)
            startActivity(intent)
        }

        // Configura la acción del botón para ir al perfil del usuario.
        val botonPerfil = findViewById<ImageView>(R.id.profileButton)
        botonPerfil.setOnClickListener {
            val intent = Intent(this, activity_usuario::class.java)
            startActivity(intent)
        }

        // Configura la acción del botón para confirmar la compra.
        val botonConfirmar = findViewById<Button>(R.id.botonComprar)
        botonConfirmar.setOnClickListener {
            val intent = Intent(this, activity_confirmarcarrito::class.java)
            startActivity(intent)
        }
    }
}