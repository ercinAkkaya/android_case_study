package com.example.android_case_study.domain.repository

import androidx.paging.PagingData
import com.example.android_case_study.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        filter: String?,
        order: String?,
        brand: String?,
        model: String?,
        sortedBy: String?
    ): Flow<PagingData<Product>>
}
