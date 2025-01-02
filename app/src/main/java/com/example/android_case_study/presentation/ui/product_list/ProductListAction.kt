package com.example.android_case_study.presentation.ui.product_list


sealed class ProductListAction {
    data class OnInputChange(val input: String) : ProductListAction()
    data class OnFilter(val minPrice: Double) : ProductListAction()
    data object ToggleBottomSheet : ProductListAction()
    data object Refresh : ProductListAction()
    data class AddFavorite(val productName: String) : ProductListAction()
    data class DeleteFavorite(val productName: String) : ProductListAction()
}