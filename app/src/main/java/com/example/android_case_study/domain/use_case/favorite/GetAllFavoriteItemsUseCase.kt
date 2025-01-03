package com.example.android_case_study.domain.use_case.favorite

import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllFavoriteItemsUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    fun getAllFavoriteItems(): Flow<Resource<List<FavoriteEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val favoriteItems = repository.getAllFavoriteItems()
            if (favoriteItems.isNotEmpty()) {
                emit(Resource.Success(favoriteItems))
            } else {
                emit(Resource.Error("No favorite items found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while fetching favorite items: ${e.message}"))
        }
    }
}