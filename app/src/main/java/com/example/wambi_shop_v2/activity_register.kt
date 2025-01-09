package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.Toast
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

        val modoInvitadoButton: Button = findViewById(R.id.Modo_Invitado)
        modoInvitadoButton.setOnClickListener {

            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)


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

                }
            }.start()


        }

    }
}

