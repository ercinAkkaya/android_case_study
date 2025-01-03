package com.example.android_case_study.data.remote.service

import com.example.android_case_study.data.remote.dto.response.ProductResponseDto
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts(): ProductResponseDto
}
