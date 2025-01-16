package com.example.wambi_shop_v2

import Producto
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val producto = intent.getParcelableExtra<Producto>("producto")

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

        val resId = resources.getIdentifier(producto?.imagen ?: "ic_error", "drawable", packageName)

        if (resId != 0) {
            productImage.setImageResource(resId)
        } else {
            productImage.setImageResource(R.drawable.ic_error)
        }

        addToCartButton.setOnClickListener {
            Toast.makeText(this, "$name added to cart", Toast.LENGTH_SHORT).show()
            // Logic to add the product to the cart
        }

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
        val heartIcon = findViewById<ImageView>(R.id.heartIcon)

        // Cargar la animación
        val heartAnimation = AnimationUtils.loadAnimation(this, R.anim.heart_animation)

        // Hacer que la animación se active cuando el corazón sea clickeado
        heartIcon.setOnClickListener {
            // Aplicar la animación
            heartIcon.startAnimation(heartAnimation)

            // Puedes agregar alguna acción adicional aquí, como cambiar el estado del corazón (favorito o no)
            Toast.makeText(this, "¡Corazón clickeado!", Toast.LENGTH_SHORT).show()
        }

    }
}



