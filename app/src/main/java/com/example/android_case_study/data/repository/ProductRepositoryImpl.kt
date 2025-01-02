package com.example.android_case_study.data.repository

import com.example.android_case_study.data.remote.dto.response.ProductResponseDto
import com.example.android_case_study.data.remote.service.ProductAPI
import com.example.android_case_study.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productAPI: ProductAPI
) : ProductRepository {
    override suspend fun getProducts(): ProductResponseDto {
        return productAPI.getProducts()
    }
}