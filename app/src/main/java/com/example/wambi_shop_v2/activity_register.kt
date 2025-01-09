package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Configuramos el botón "Modo Invitado"
        val modoInvitadoButton = findViewById<Button>(R.id.Modo_Invitado)
        modoInvitadoButton.setOnClickListener {
            // Iniciar el servicio para la cuenta regresiva de 30 segundos
            val serviceIntent = Intent(this, GuestModeService::class.java)
            startService(serviceIntent)

            // Navegar a la actividad de HallShop
            val intent = Intent(this, activity_hallshop::class.java)  // Aquí cambia a HallShopActivity
            startActivity(intent)
        }
    }
}
