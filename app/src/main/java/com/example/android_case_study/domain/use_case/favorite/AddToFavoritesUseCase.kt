package com.example.android_case_study.domain.use_case.favorite

import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.domain.repository.FavoriteRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend fun execute(favoriteItem: FavoriteEntity) {
        repository.insertFavoriteItem(favoriteItem)
    }
}
