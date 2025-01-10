package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Configuración para el botón "Volver a Login"
        findViewById<Button>(R.id.boton_volver_login).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Configuración para el botón "Acceder a Registro"
        findViewById<Button>(R.id.acceder_registro).setOnClickListener {
            startActivity(Intent(this, activity_hallshop::class.java))
        }

        // Configuración para el botón "Modo Invitado"
        findViewById<Button>(R.id.Modo_Invitado).setOnClickListener {
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.d("Countdown", "Seconds remaining: ${millisUntilFinished / 1000}")
                }

                override fun onFinish() {
                    Log.d("Countdown", "onFinish called!")
                    Toast.makeText(this@activity_register, "Plazo del Modo Activo Terminado XD", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }.start()
        }
    }
}
