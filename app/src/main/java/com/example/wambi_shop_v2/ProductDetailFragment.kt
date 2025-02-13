package com.example.wambi_shop_v2

import Producto
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_product_detail)

        // Recuperar el producto del Intent
        val producto = intent.getParcelableExtra<Producto>("producto")

        // Referencias a la UI
        val productName = findViewById<TextView>(R.id.productName)
        val productPrice = findViewById<TextView>(R.id.productPrice)
        val productImage = findViewById<ImageView>(R.id.productImage)
        val productDescription = findViewById<TextView>(R.id.productDescription)
        val addToCartButton = findViewById<Button>(R.id.btnAgregarCarrito)

        val name = producto?.nombre ?: "Unknown"
        val price = producto?.precio ?: 0.0
        val imageResId = intent.getIntExtra("PRODUCT_IMAGE", R.drawable.ic_error)

        productName.text = name
        productPrice.text = "$price €"
        productImage.setImageResource(imageResId)
        productDescription.text = producto?.descripcion ?: "No tiene descripcion"

        // Cargar la imagen usando el nombre de recurso
        val resId = resources.getIdentifier(producto?.imagen ?: "ic_error", "drawable", packageName)
        if (resId != 0) {
            productImage.setImageResource(resId)
        } else {
            productImage.setImageResource(R.drawable.ic_error)
        }

        // Botón "Agregar al carrito": se utiliza el metodo addToCartButtom
        addToCartButton.setOnClickListener {
            // Se asume que el usuario tiene ID 1; si 'producto' es nulo se pasa un valor por defecto
            val success = Database().addToCartButtom(
                context = this,
                producto = producto ?: Producto(0, "Undefined", "", 0.0, 0, "", ""),
                id_usuario = 1,
                cantidad = 1
            )
            if (success) {
                Toast.makeText(this, "$name added to cart", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error adding product to cart", Toast.LENGTH_SHORT).show()
            }
        }

        // Navegación a otras pantallas
        val botonHome = findViewById<ImageView>(R.id.homeButton)
        botonHome.setOnClickListener {
            val intent = Intent(this, activity_hallshop::class.java)
            startActivity(intent)
        }
        val botonCarrito = findViewById<ImageView>(R.id.cartButton)
        botonCarrito.setOnClickListener {
            val intent = Intent(this, activity_carrito::class.java)
            startActivity(intent)
        }
        val botonPerfil = findViewById<ImageView>(R.id.profileButton)
        botonPerfil.setOnClickListener {
            val intent = Intent(this, activity_usuario::class.java)
            startActivity(intent)
        }

        // Animación del corazón
        val heartIcon = findViewById<ImageView>(R.id.heartIcon)
        val heartAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_animation)
        heartIcon.setOnClickListener {
            heartIcon.startAnimation(heartAnimation)
            Toast.makeText(this, "¡Corazón clickeado!", Toast.LENGTH_SHORT).show()
        }
    }
}
