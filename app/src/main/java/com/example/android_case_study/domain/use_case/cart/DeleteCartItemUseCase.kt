package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.domain.repository.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItemId: String) {
        repository.deleteCartItem(cartItemId)
    }
}