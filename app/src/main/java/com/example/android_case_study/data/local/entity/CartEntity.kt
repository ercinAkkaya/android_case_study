package com.example.android_case_study.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_case_study.core.util.Constants

@Entity(tableName = Constants.CART_DATABASE_NAME)
data class CartEntity(
    @PrimaryKey val id: String,
    val brand: String,
    val createdAt: String,
    val description: String,
    val image: String,
    val model: String,
    val name: String,
    val price: String,
    val quantity: Int = 1
)
