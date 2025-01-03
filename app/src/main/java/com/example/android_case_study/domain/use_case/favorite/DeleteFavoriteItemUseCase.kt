package com.example.android_case_study.domain.use_case.favorite

import com.example.android_case_study.domain.repository.FavoriteRepository
import com.example.android_case_study.data.local.entity.FavoriteEntity
import javax.inject.Inject

class DeleteFavoriteItemUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(favoriteItem: FavoriteEntity) {
        repository.deleteFavoriteItem(favoriteItem)
    }
}