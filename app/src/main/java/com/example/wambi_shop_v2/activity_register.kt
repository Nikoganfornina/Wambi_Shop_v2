package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_register : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Encuentra el botón de registro y establece el OnClickListener
        val botonInicio = findViewById<Button>(R.id.boton_volver_login)
        botonInicio.setOnClickListener {
            // Intent para ir a la actividad de registro
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Encuentra el botón de registro y establece el OnClickListener
        val botonAccederPagina = findViewById<Button>(R.id.acceder_registro)
        botonAccederPagina.setOnClickListener {
            // Intent para ir a la actividad de registro
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

        }


    }
}