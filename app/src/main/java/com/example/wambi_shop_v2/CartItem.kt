package com.example.wambi_shop_v2

import Producto
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItem(
    val idCarrito: Int,
    val producto: Producto,
    val cantidad: Int
) : Parcelable