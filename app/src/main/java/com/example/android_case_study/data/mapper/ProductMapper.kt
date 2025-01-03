package com.example.android_case_study.data.mapper

import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.data.remote.dto.response.ProductResponseDtoItem
import com.example.android_case_study.domain.model.CartItem
import com.example.android_case_study.domain.model.FavoriteItem
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.presentation.ui.detail.model.DetailModel


fun Product.toDetailModel() = DetailModel(
    imageUrl = image,
    name = name,
    description = description,
    price = price.toDouble(),
    isFavorite = false
)

fun ProductResponseDtoItem.toCartEntity() = CartEntity(
    id = id,
    brand = brand,
    createdAt = createdAt,
    description = description,
    image = image,
    model = model,
    name = name,
    price = price
)

fun CartEntity.toCartItem() = CartItem(
    id = id,
    brand = brand,
    createdAt = createdAt,
    description = description,
    image = image,
    model = model,
    name = name,
    price = price,
    quantity = quantity
)

fun ProductResponseDtoItem.toFavoriteEntity() = FavoriteEntity(
    id = id,
    brand = brand,
    createdAt = createdAt,
    description = description,
    image = image,
    model = model,
    name = name,
    price = price
)

fun FavoriteEntity.toFavoriteItem() = FavoriteItem(
    id = id,
    brand = brand,
    createdAt = createdAt,
    description = description,
    image = image,
    model = model,
    name = name,
    price = price
)