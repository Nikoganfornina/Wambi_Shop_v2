package com.example.wambi_shop_v2
/*
import Producto
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wambi_shop_v2.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private lateinit var producto: Producto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inicializar el binding
        val binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        // Obtener el id del producto desde los argumentos
        val productoId = arguments?.getInt("PRODUCTO_ID") ?: return binding.root

        // Obtener el producto desde la base de datos usando el id
        producto = getProductById(productoId) ?: return binding.root // Si no encuentra el producto, termina aquí

        // Mostrar la información del producto en los TextViews
        binding.productName.text = producto.nombre // Nombre del producto
        binding.productDescription.text = producto.descripcion // Descripción del producto
        binding.productPrice.text = "$${producto.precio}" // Precio del producto

        return binding.root
    }

    // Método para obtener un producto por ID desde la base de datos
    private fun getProductById(productId: Int): Producto? {
        val db = DatabaseHelper(requireContext())
        return db.getProductById(productId)
    }
}

 */
