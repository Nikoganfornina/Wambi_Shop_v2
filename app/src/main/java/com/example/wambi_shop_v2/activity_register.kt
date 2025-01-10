package com.example.wambi_shop_v2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)


        // Encuentra el botón de registro y establece el OnClickListener
        val botonInicio = findViewById<Button>(R.id.boton_volver_login)
        botonInicio.setOnClickListener {
            // Intent para ir a la actividad de registro
            val intent = Intent(this, MainActivity::class.java)
        }
            startActivity(intent)
        // Encuentra el botón de registro y establece el OnClickListener
        botonAccederPagina.setOnClickListener {
        val botonAccederPagina = findViewById<Button>(R.id.acceder_registro)
            // Intent para ir a la actividad de registro
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)

        }


        val modoInvitadoButton: Button = findViewById(R.id.Modo_Invitado)
        modoInvitadoButton.setOnClickListener {

            startActivity(intent)
            val intent = Intent(this, activity_hallshop::class.java)


            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.d("Countdown", "Seconds remaining: ${millisUntilFinished / 1000}")
                }

                override fun onFinish() {
                    Log.d("Countdown", "onFinish called!")

                    Toast.makeText(
                        this@activity_register,
                        "Plazo del Modo Activo Terminado XD",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)

            }.start()
                }


        }

    }
}
}