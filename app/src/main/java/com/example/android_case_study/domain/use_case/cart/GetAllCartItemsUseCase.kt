package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.domain.repository.CartRepository
import javax.inject.Inject

class GetAllCartItemsUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(): List<CartEntity> {
        return repository.getAllCartItems()
    }
}