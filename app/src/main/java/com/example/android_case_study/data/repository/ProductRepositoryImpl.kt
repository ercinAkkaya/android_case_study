package com.example.android_case_study.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android_case_study.data.remote.service.ProductService
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductService
) : ProductRepository {

    override fun getProducts(
        filter: String?,
        order: String?,
        brand: String?,
        model: String?,
        sortedBy: String?
    ): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingDataSource(api, filter, order, brand, model, sortedBy)
            }
        ).flow
    }
}