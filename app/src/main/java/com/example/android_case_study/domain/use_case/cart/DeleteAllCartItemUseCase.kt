package com.example.android_case_study.domain.use_case.cart

import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteAllCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    fun deleteAllCartItems(): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.deleteAllCartItems()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while deleting all cart items"))
        }
    }
}
