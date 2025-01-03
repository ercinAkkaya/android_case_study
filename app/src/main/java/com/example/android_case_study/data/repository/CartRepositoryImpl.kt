package com.example.android_case_study.data.repository

import com.example.android_case_study.data.local.dao.CartDao
import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {
    override suspend fun insertCartItem(cartItem: CartEntity) {
        if (cartDao.getItemCount(cartItem.id) > 0) {
            cartDao.incrementQuantity(cartItem.id)
        } else {
            cartDao.insertCartItem(cartItem)
        }
    }

    override suspend fun getAllCartItems(): List<CartEntity> {
        return cartDao.getAllCartItems()
    }

    override suspend fun deleteCartItem(id: String) {
        cartDao.deleteCartItem(id)
    }

    override suspend fun deleteAllCartItems() {
        cartDao.deleteAllCartItems()
    }

    override suspend fun getCartItemCount(): Int {
        return cartDao.getCartItemCount()
    }
}
