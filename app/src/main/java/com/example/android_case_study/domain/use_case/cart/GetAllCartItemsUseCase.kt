package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCartItemsUseCase @Inject constructor(
    private val repository: CartRepository
) {
    fun getAllCartItems() : Flow<Resource<List<CartEntity>>> = flow {

        try {
            emit(Resource.Loading())
            val cartItems = repository.getAllCartItems()
            if (cartItems.isNotEmpty()) {
                emit(Resource.Success(cartItems))
            } else {
                emit(Resource.Error("No cart items found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while fetching cart items : ${e.message}"))
        }

    }
}