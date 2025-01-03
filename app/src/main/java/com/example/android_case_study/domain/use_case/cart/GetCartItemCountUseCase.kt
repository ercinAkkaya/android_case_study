package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemCountUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getCartItemCount()
    }
}