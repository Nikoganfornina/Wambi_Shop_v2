package com.example.wambi_shop_v2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityEliminarProductoAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Para el diseño a pantalla completa
        setContentView(R.layout.activity_eliminarproductoadmin)

        // Ajuste de márgenes para las barras del sistema (estado y navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Eliminar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al botón de "Volver"
        val btnVolver = findViewById<Button>(R.id.volver)
        btnVolver.setOnClickListener {
            finish()  // Finaliza la actividad y regresa a la anterior
        }

        // Referencia al campo donde el administrador ingresa el nombre del producto
        val etNombreProducto = findViewById<EditText>(R.id.EliminarCuadro)

        // Referencia al botón de "Eliminar Producto"
        val btnEliminar = findViewById<Button>(R.id.deleteproductbutton)
        btnEliminar.setOnClickListener {
            // Obtenemos el texto ingresado en el campo de nombre del producto
            val nombre = etNombreProducto.text.toString()

            // Validamos si el campo de nombre está vacío
            if (nombre.isBlank()) {
                Toast.makeText(this, "Por favor, ingrese el nombre del producto.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener  // Detener la acción si el campo está vacío
            }

            try {
                // Instancia de la clase DBHelper para interactuar con la base de datos
                val dbInstance = Database.DBHelper(this)

                // Llamar a la función para eliminar el producto usando el nombre
                val exito = dbInstance.deleteProducto(nombre)

                // Notificar al usuario si la eliminación fue exitosa
                if (exito) {
                    Toast.makeText(this, "Producto eliminado con éxito.", Toast.LENGTH_SHORT).show()
                    etNombreProducto.text.clear()  // Limpiar el campo de texto
                } else {
                    Toast.makeText(this, "No se encontró el producto con ese nombre. Verifique e intente nuevamente.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Manejo de excepciones si algo sale mal
                Toast.makeText(this, "Error al eliminar el producto: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
