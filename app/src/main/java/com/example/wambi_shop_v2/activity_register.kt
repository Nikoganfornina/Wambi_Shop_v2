package com.example.wambi_shop_v2

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class activity_register : AppCompatActivity() {

    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar base de datos
        val dbHelper = Database.DBHelper(this)
        database = dbHelper.writableDatabase

        // Botón para volver al login
        val botonInicio = findViewById<Button>(R.id.boton_volver_login)
        botonInicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Botón de acceso al modo invitado
        val botonInvitado = findViewById<Button>(R.id.Modo_Invitado)
        botonInvitado.setOnClickListener {
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)
            startGuestModeTimer()
        }

        // Botón de registro
        val botonAccederPagina = findViewById<Button>(R.id.acceder_registro)
        botonAccederPagina.setOnClickListener {
            val emailField = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val passwordField = findViewById<EditText>(R.id.Contraseña)
            val confirmPasswordField = findViewById<EditText>(R.id.ContraseñarConfirmar)

            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val confirmPassword = confirmPasswordField.text.toString().trim()

            if (validateInputs(email, password, confirmPassword)) {
                saveUser(email, password)
            }
        }
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(this, "El correo es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "La contraseña es obligatoria", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveUser(email: String, password: String) {
        val values = ContentValues().apply {
            put("email", email)
            put("contrasena", password)
        }

        val result = database.insert("usuarios", null, values)
        if (result != -1L) {
            Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startGuestModeTimer() {
        Toast.makeText(this, "Modo invitado activado por 30 segundos", Toast.LENGTH_SHORT).show()
        Handler(mainLooper).postDelayed({
            Toast.makeText(this, "Modo de prueba terminado, regístrese.", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 30_000)
    }

    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }
}
