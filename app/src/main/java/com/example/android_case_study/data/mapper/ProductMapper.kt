package com.example.android_case_study.data.mapper

import com.example.android_case_study.data.remote.dto.response.ProductResponse
import com.example.android_case_study.domain.model.Product

fun ProductResponse.toDomain(): Product {
    return Product(
        id = id.orEmpty(),
        name = name.orEmpty(),
        price = price.toDoubleOrNull() ?: 0.0,
        description = description.orEmpty(),
        brand = brand.orEmpty(),
        model = model.orEmpty(),
        image = image.orEmpty(),
        createdAt = createdAt.orEmpty(),
        isFavorite = "false",
        isInCart = "false",
        quantity = "0"
    )
}

 //TODO: burda cart ve favorite için mapperler yazılacak ilgili alanların entityleri yazıldığında

fun List<ProductResponse>.toDomainList(): List<Product> {
    return map { it.toDomain() }
}