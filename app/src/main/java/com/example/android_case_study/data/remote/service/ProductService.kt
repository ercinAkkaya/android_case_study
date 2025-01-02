package com.example.android_case_study.data.remote.service

import com.example.android_case_study.data.remote.dto.response.ProductResponse
import com.example.android_case_study.data.remote.dto.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    suspend fun getProducts(
        @Query("filter") filter: String?,
        @Query("order") order: String?,
        @Query("brand") brand: String?,
        @Query("model") model: String?,
        @Query("sortedBy") sortedBy: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): ResponseWrapper<ProductResponse>
}