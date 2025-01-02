package com.example.android_case_study.domain.model

import com.example.android_case_study.presentation.ui.detail.model.DetailModel

data class Product(
    val brand: String,
    val createdAt: String,
    val description: String,
    val id: String,
    val image: String,
    val model: String,
    val name: String,
    val price: String
)

fun Product.toDetailModel() = DetailModel(
    imageUrl = image,
    name = name,
    description = description,
    price = price.toDouble(),
    isFavorite = false
)
