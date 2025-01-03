package com.example.android_case_study.domain.repository

import com.example.android_case_study.data.local.entity.CartEntity

interface CartRepository {
    suspend fun insertCartItem(cartItem: CartEntity)
    suspend fun getAllCartItems(): List<CartEntity>
    suspend fun deleteCartItem(id: String)
    suspend fun deleteAllCartItems()
    suspend fun getCartItemCount(): Int

}
