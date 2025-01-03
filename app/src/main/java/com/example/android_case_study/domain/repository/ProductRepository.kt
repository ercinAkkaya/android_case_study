package com.example.android_case_study.domain.repository

import com.example.android_case_study.data.remote.dto.response.ProductResponseDto

interface ProductRepository {
    suspend fun getProducts(): ProductResponseDto
}
