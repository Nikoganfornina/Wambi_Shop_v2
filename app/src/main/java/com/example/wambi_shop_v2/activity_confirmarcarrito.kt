package com.example.wambi_shop_v2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class activity_confirmarcarrito : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnProcederPago: Button
    private lateinit var tvTotalPrice: TextView
    private var carritoItems: ArrayList<CartItem>? = null
    private var totalPrice: Double = 0.0
    // Para efectos de simulación, asumimos que el nombre del usuario es "Juan Pérez"
    private val userName = "Juan Pérez"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmarcarrito)

        radioGroup = findViewById(R.id.radioGroupPago)
        btnProcederPago = findViewById(R.id.btnProcederPago)
        tvTotalPrice = findViewById(R.id.tvTotalLabel)

        carritoItems = intent.getParcelableArrayListExtra("carritoItems")
        calcularTotal()
        tvTotalPrice.text = "Total: $totalPrice €"

        btnProcederPago.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val metodoPago = selectedRadioButton.text.toString()
                val intent = Intent(this, activity_ticket::class.java)
                intent.putExtra("userName", userName)
                intent.putParcelableArrayListExtra("carritoItems", carritoItems)
                intent.putExtra("totalPrice", totalPrice)
                intent.putExtra("metodoPago", metodoPago)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, selecciona un método de pago", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcularTotal() {
        totalPrice = 0.0
        carritoItems?.forEach { item ->
            totalPrice += (item.producto.precio ?: 0.0) * item.cantidad
        }
    }
}
