package com.example.android_case_study.data.remote.dto.response

import com.example.android_case_study.domain.model.Product

class ProductResponseDto : ArrayList<ProductResponseDtoItem>()

fun ProductResponseDto.toDomainModel(): List<Product> {
    return this.map {
        Product(
            brand = it.brand,
            createdAt = it.createdAt,
            description = it.description,
            id = it.id,
            image = it.image,
            model = it.model,
            name = it.name,
            price = it.price
        )
    }
}
