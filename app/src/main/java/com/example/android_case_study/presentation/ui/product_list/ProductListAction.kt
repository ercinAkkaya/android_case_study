package com.example.android_case_study.presentation.ui.product_list

import com.example.android_case_study.core.base.UIAction

sealed class ProductListAction {
    data class OnInputChange(val input: String) : ProductListAction()
    data class OnFilter(val minPrice: Double) : ProductListAction()
    data object ToggleBottomSheet : ProductListAction()
    data object Refresh : ProductListAction()
}