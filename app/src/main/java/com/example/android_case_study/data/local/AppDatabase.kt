package com.example.android_case_study.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_case_study.data.local.dao.CartDao
import com.example.android_case_study.data.local.dao.FavoriteDao
import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.data.local.entity.FavoriteEntity

@Database(entities = [CartEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun favoriteDao(): FavoriteDao
}
