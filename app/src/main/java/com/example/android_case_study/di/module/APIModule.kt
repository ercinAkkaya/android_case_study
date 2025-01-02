package com.example.android_case_study.di.module

import com.example.android_case_study.core.util.Constants.BASE_URL
import com.example.android_case_study.data.remote.service.ProductService
import com.example.android_case_study.data.repository.ProductRepositoryImpl
import com.example.android_case_study.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object APIModule {

    @Singleton
    @Provides
    fun provideApi(): ProductService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }

}