package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/*  TODO PENDIENTE A REVISION */

class activity_inicioSesion {
    class activity_inicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }
    class  activity_inicioSesion : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val miBoton: Button = findViewById(R.id.boton_acceder)
            miBoton.setOnClickListener {
                val intent = Intent(this,activity_hallshop::class.java)
                startActivity(intent)
            }
        }

    }
}
}