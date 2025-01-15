package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Metodo que se ejecuta cuando se crea la actividad.
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el diseño de borde a borde para la actividad.
        setContentView(R.layout.activity_main) // Establece el layout de la actividad.

        /* // Agregar el OnClickListener al botón
         val botonAcceder: Button = findViewById(R.id.boton_acceder) // Obtiene el botón por su ID
         botonAcceder.setOnClickListener {
             // Muestra un Toast para verificar que el botón funciona
             Toast.makeText(this, "Botón Acceder pulsado", Toast.LENGTH_SHORT).show()


             val builder = AlertDialog.Builder(this)
             builder.setMessage("Botón Acceder pulsado")
                 .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                 .create()
                 .show()

         }*/

        // Ajusta el padding de la vista principal para los márgenes del sistema, considerando las barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.InicioApp)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del botón de registro.
        val botonRegistro = findViewById<Button>(R.id.boton_registro)
        botonRegistro.setOnClickListener {
            val intent = Intent(this, activity_register::class.java) // Redirige al registro de usuario.
            startActivity(intent)
        }

        // Configuración del texto para redirigir al registro.
        val botonRegistroIr = findViewById<TextView>(R.id.ir_registro)
        botonRegistroIr.setOnClickListener {
            val intent = Intent(this, activity_register::class.java) // Redirige al registro de usuario.
            startActivity(intent)
        }

        // Configuración del botón de acceder.
        val botonAccederPagina = findViewById<Button>(R.id.boton_acceder)
        botonAccederPagina.setOnClickListener {
            val botonCorreo = findViewById<EditText?>(R.id.Correo).text.toString() // Obtiene el correo ingresado.
            val botonContrasena = findViewById<EditText?>(R.id.Contraseña).text.toString() // Obtiene la contraseña ingresada.

            // Verifica si las credenciales son correctas (usuario admin).
            if (botonCorreo == "admin" && botonContrasena == "admin") {
                val intent = Intent(this, activity_adminmain::class.java) // Redirige al panel de administrador.
            } else {
                // Si las credenciales no son de admin, verifica en la base de datos.
                val db = Database.DBHelper(this)
                if (db.verificarUsuario(botonCorreo, botonContrasena)) {
                    // Si el usuario existe, redirige a la tienda.
                    val intent = Intent(this, activity_hallshop::class.java)
                    startActivity(intent)
                } else {
                    // Si las credenciales son incorrectas, muestra un mensaje de error.
                    Toast.makeText(this, "ERROR: Credenciales incorrectas", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
