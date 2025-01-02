package com.example.android_case_study.domain.use_case.home

import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.data.remote.dto.response.toDomainModel
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    fun getProducts():Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getProducts()
            if (products.isNotEmpty()) {
                emit(Resource.Success(products.toDomainModel()))
            } else {
                emit(Resource.Error("No products found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while fetching products"))
        }
    }
}