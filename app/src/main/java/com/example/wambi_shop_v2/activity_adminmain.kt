
package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_adminmain : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adminmain)

        // Aplicar los insets directamente al root view de la actividad
        val rootView = findViewById<View>(android.R.id.content)  // Obtén el root layout
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el botón de desconectar
        val botonDesconectarAdmin = findViewById<Button>(R.id.desconectarseAdmin)
        botonDesconectarAdmin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Configurar el botón para añadir productos
        val botonAnadir = findViewById<Button>(R.id.addproductbutton)
        botonAnadir.setOnClickListener {
            val intent = Intent(this, activity_insertarproductoadmin::class.java)
            startActivity(intent)
        }

        // Configurar el botón para eliminar productos
        val botonEliminar = findViewById<Button>(R.id.deleteproductbutton)
        botonEliminar.setOnClickListener {
            val intent = Intent(this, activity_eliminarproductoadmin::class.java)
            startActivity(intent)
        }
    }
}
