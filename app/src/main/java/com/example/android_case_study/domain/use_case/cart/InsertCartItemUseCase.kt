package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.domain.repository.CartRepository
import javax.inject.Inject

class InsertCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    // TODO
    suspend operator fun invoke(cartItem: CartEntity) {
        repository.insertCartItem(cartItem)
    }
}
