package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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


        val botonInicio = findViewById<Button>(R.id.boton_volver_login)
        botonInicio.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val botonAccederPagina = findViewById<Button>(R.id.acceder_registro)
        botonAccederPagina.setOnClickListener {

            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

        }

        val botonInvitado = findViewById<Button>(R.id.Modo_Invitado)
        botonInvitado.setOnClickListener {

            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

        }

        val tienesCuenta = findViewById<TextView>(R.id.Tienes_Cuenta)
        tienesCuenta.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}