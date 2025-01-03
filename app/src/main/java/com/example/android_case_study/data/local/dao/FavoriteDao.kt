package com.example.android_case_study.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_case_study.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteItem: FavoriteEntity)

    @Query("SELECT * FROM favorite_database")
    suspend fun getAllFavoriteItems(): List<FavoriteEntity>

    @Query("DELETE FROM favorite_database WHERE id = :id")
    suspend fun deleteFavoriteItem(id: String)

    @Query("SELECT COUNT(*) FROM favorite_database")
    suspend fun getFavoriteItemCount(): Int
}
