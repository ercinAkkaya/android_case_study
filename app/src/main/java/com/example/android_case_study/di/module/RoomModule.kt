package com.example.android_case_study.di.module

import android.content.Context
import androidx.room.Room
import com.example.android_case_study.data.local.AppDatabase
import com.example.android_case_study.data.local.dao.CartDao
import com.example.android_case_study.data.local.dao.FavoriteDao
import com.example.android_case_study.data.repository.CartRepositoryImpl
import com.example.android_case_study.data.repository.FavoriteRepositoryImpl
import com.example.android_case_study.domain.repository.CartRepository
import com.example.android_case_study.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {



    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "product_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.cartDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteDao: FavoriteDao): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteDao)
    }

}