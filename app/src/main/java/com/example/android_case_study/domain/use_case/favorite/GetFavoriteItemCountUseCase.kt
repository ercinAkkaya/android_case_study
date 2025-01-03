package com.example.android_case_study.domain.use_case.favorite

import com.example.android_case_study.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteItemCountUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(): Int {
        return favoriteRepository.getFavoriteItemCount()
    }
}