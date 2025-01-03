package com.example.android_case_study.domain.repository

import com.example.android_case_study.data.local.entity.FavoriteEntity

interface FavoriteRepository {
    suspend fun insertFavoriteItem(favoriteItem: FavoriteEntity)
    suspend fun getAllFavoriteItems(): List<FavoriteEntity>
    suspend fun deleteFavoriteItem(id: String)
    suspend fun getFavoriteItemCount(): Int
}
