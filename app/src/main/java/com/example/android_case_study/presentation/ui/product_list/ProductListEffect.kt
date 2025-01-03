package com.example.android_case_study.presentation.ui.product_list

import com.example.android_case_study.presentation.ui.detail.model.DetailModel

sealed class ProductListEffect {

    data class ShowError(val message: String) : ProductListEffect()

    data class ShowToastMessage(val message: String) : ProductListEffect()

    data class NavigateToDetail(val productDetailModel: DetailModel) : ProductListEffect()
}
