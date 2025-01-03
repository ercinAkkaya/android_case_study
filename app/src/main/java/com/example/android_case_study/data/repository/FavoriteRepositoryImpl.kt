package com.example.android_case_study.data.repository

import com.example.android_case_study.data.local.dao.FavoriteDao
import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override suspend fun insertFavoriteItem(favoriteItem: FavoriteEntity) {
        favoriteDao.insertFavoriteItem(favoriteItem)
    }

    override suspend fun getAllFavoriteItems(): List<FavoriteEntity> {
        return favoriteDao.getAllFavoriteItems()
    }

    override suspend fun deleteFavoriteItem(favoriteItem: FavoriteEntity) {
        favoriteDao.deleteFavoriteItem(favoriteItem)
    }
}
