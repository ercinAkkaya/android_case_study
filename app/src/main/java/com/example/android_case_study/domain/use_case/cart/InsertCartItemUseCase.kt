package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.domain.repository.CartRepository
import com.example.android_case_study.data.local.entity.CartEntity
import javax.inject.Inject

class InsertCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartEntity) {
        repository.insertCartItem(cartItem)
    }


}