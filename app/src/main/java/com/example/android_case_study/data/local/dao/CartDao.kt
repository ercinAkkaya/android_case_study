package com.example.android_case_study.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_case_study.data.local.entity.CartEntity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartEntity)

    @Query("SELECT * FROM cart_database")
    suspend fun getAllCartItems(): List<CartEntity>

    @Query("DELETE FROM cart_database WHERE id = :cartItemId")
    suspend fun deleteCartItem(cartItemId: String)

    @Query("UPDATE cart_database SET quantity = quantity + 1 WHERE id = :cartItemId")
    suspend fun incrementQuantity(cartItemId: String)

    @Query("SELECT COUNT(*) FROM cart_database WHERE id = :cartItemId")
    suspend fun getItemCount(cartItemId: String): Int
}