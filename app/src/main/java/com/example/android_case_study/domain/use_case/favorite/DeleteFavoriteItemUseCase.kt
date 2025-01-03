package com.example.android_case_study.domain.use_case.favorite

import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteItemUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    fun deleteFavoriteItem(id: String): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.deleteFavoriteItem(id)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while deleting favorite item: ${e.message}"))
        }
    }
}
