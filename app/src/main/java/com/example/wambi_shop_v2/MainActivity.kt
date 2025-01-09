package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Agregar el OnClickListener al botón
        val botonAcceder: Button = findViewById(R.id.boton_acceder) // Obtiene el botón por su ID
        botonAcceder.setOnClickListener {
            // Muestra un Toast para verificar que el botón funciona
            Toast.makeText(this, "Botón Acceder pulsado", Toast.LENGTH_SHORT).show()


            val builder = AlertDialog.Builder(this)
            builder.setMessage("Botón Acceder pulsado")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()

        }

        // Ajustar el padding de la vista para los márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.InicioApp)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Encuentra el botón de registro y establece el OnClickListener
        val botonRegistro = findViewById<Button>(R.id.boton_registro)
        botonRegistro.setOnClickListener {
            // Intent para ir a la actividad de registro
            val intent = Intent(this, activity_register::class.java)
            startActivity(intent)
        }
        // Encuentra el botón de registro y establece el OnClickListener
        val botonRegistroIr = findViewById<TextView>(R.id.ir_registro)
        botonRegistroIr.setOnClickListener {
            // Intent para ir a la actividad de registro
            val intent = Intent(this, activity_register::class.java)
            startActivity(intent)
        }

        // Encuentra el botón de registro y establece el OnClickListener
        val botonAccederPagina = findViewById<Button>(R.id.boton_acceder)
        botonAccederPagina.setOnClickListener {
            // Intent para ir a la actividad de registro
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)
        }







    }
}
