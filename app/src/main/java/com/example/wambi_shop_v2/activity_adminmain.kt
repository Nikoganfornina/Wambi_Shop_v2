package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val botonDesconectarAdmin = findViewById<Button>(R.id.desconectarseAdmin)
        botonDesconectarAdmin.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        val botonAnadir = findViewById<Button>(R.id.buttonAnadir)
        botonAnadir.setOnClickListener {

            val intent = Intent(this, activity_insertarproductoadmin::class.java)
            startActivity(intent)

        }
        val botonEliminar = findViewById<Button>(R.id.buttonEliminar)
        botonEliminar.setOnClickListener {

            val intent = Intent(this, activity_eliminarproductoadmin::class.java)
            startActivity(intent)

        }
        val botonEditar = findViewById<Button>(R.id.buttonEditar)
        botonEditar.setOnClickListener {

            val intent = Intent(this, activity_editarproductoadmin::class.java)
            startActivity(intent)

        }
    }
}