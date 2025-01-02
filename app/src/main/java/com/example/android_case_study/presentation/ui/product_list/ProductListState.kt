package com.example.android_case_study.presentation.ui.product_list

import com.example.android_case_study.domain.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val productList: List<Product> = emptyList(),
    val error: String = ""
)
