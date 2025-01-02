package com.example.android_case_study.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val brand: String,
    val model: String,
    val image: String,
    val createdAt: String,
    var isFavorite: String,
    var isInCart: String,
    var quantity: String
)