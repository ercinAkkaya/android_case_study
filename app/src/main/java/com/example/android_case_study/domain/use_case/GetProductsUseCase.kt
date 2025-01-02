package com.example.android_case_study.domain.use_case

import com.example.android_case_study.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    fun execute(
        filter: String?,
        order: String?,
        brand: String?,
        model: String?,
        sortedBy: String?
    ) = productRepository.getProducts(filter, order, brand, model, sortedBy)
}