package com.example.android_case_study.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteItem: FavoriteEntity)

    @Query("SELECT * FROM favorite_database")
    suspend fun getAllFavoriteItems(): List<FavoriteEntity>

    @Delete
    suspend fun deleteFavoriteItem(favoriteItem: FavoriteEntity)
}