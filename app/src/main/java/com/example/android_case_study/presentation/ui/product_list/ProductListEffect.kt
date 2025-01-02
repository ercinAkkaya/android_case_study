package com.example.android_case_study.presentation.ui.product_list

import com.example.android_case_study.core.base.UIEffect

sealed class ProductListEffect{

    data class ShowError(val message: String) : ProductListEffect()

    data object ShowLoading : ProductListEffect()

    data object HideLoading : ProductListEffect()

}