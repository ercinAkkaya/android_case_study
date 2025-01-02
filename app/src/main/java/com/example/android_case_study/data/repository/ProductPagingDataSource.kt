package com.example.android_case_study.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android_case_study.data.mapper.toDomain
import com.example.android_case_study.data.remote.service.ProductService
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.data.remote.dto.response.ProductResponse

class ProductPagingDataSource(
    private val api: ProductService,
    private val filter: String?,
    private val order: String?,
    private val brand: String?,
    private val model: String?,
    private val sortedBy: String?
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val response = api.getProducts(
                page = page,
                filter = filter,
                order = order,
                brand = brand,
                model = model,
                sortedBy = sortedBy,
                pageSize = params.loadSize,
            )

            val products = response.data.map { it.toDomain() }

            LoadResult.Page(
                data = products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (products.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

