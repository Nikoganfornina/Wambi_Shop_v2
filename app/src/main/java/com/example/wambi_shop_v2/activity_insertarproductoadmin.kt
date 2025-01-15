package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_insertarproductoadmin : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertarproductoadmin)

        // Cambiar el root view si el ID 'main' no está presente
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referenciar los elementos del layout
        val etNombre = findViewById<EditText>(R.id.nombreCuadro)
        val etCategoria = findViewById<EditText>(R.id.CategoriaCuadro)
        val etPrecio = findViewById<EditText>(R.id.PrecioCuadro)
        val etStock = findViewById<EditText>(R.id.StockCuadro)
        val etDescripcion = findViewById<EditText>(R.id.DescripcionCuadro)
        val etImagen = findViewById<EditText>(R.id.FotoCuadro)
        val btnAddProducto = findViewById<Button>(R.id.button)
        val btnVolver = findViewById<Button>(R.id.volver)

        btnVolver.setOnClickListener {
            finish()
        }

        // Instancia de la base de datos
        val database = Database()

        btnAddProducto.setOnClickListener {
            try {
                // Obtener los valores de los campos
                val nombre = etNombre.text.toString()
                val categoria = etCategoria.text.toString()
                val precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0
                val stock = etStock.text.toString().toIntOrNull() ?: 0
                val descripcion = etDescripcion.text.toString()
                val imagen = etImagen.text.toString()

                // Validar los campos
                if (nombre.isBlank() || categoria.isBlank() || precio <= 0 || stock <= 0 || descripcion.isBlank() || imagen.isBlank()) {
                    Toast.makeText(this, "Por favor, rellena todos los campos correctamente.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Crear una instancia de la clase Database
                val dbInstance = Database.DBHelper(this)

                // Llamar a la función para añadir el producto
                val exito = dbInstance.addProducto(
                    context = this,
                    nombre = nombre,
                    categoria = categoria,
                    precio = precio,
                    stock = stock,
                    descripcion = descripcion,
                    imagen = imagen
                )

                // Notificar al usuario el resultado
                if (exito) {
                    Toast.makeText(this, "Producto añadido con éxito.", Toast.LENGTH_SHORT).show()
                    limpiarCampos(etNombre, etCategoria, etPrecio, etStock, etDescripcion, etImagen)
                } else {
                    Toast.makeText(this, "Error al añadir el producto.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para limpiar los campos después de añadir el producto
    private fun limpiarCampos(vararg editTexts: EditText) {
        editTexts.forEach { it.text.clear() }
    }
}
