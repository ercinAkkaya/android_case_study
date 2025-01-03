package com.example.android_case_study.domain.model

data class CartItem(
    val id: String,
    val brand: String,
    val createdAt: String,
    val description: String,
    val image: String,
    val model: String,
    val name: String,
    val price: String,
    val quantity: Int
)
