package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_pago : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnConfirmarPago: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        radioGroup = findViewById(R.id.radioGroupPago)
        btnConfirmarPago = findViewById(R.id.btnConfirmarPago)

        btnConfirmarPago.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val metodoPago = selectedRadioButton.text.toString()

                val resultIntent = Intent()
                resultIntent.putExtra("metodoPago", metodoPago)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, selecciona un método de pago", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
