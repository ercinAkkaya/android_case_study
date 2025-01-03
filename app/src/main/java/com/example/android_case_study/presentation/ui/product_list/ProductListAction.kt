package com.example.android_case_study.presentation.ui.product_list

import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.presentation.ui.detail.model.DetailModel

sealed class ProductListAction {
    data class OnInputChange(val input: String) : ProductListAction()
    data class OnFilter(val minPrice: Double) : ProductListAction()
    data object ToggleBottomSheet : ProductListAction()
    data object Refresh : ProductListAction()
    data class AddFavorite(val product: FavoriteEntity) : ProductListAction()
    data class DeleteFavorite(val id: String, val name: String) : ProductListAction()
    data class NavigateToDetail(val productDetail: DetailModel) : ProductListAction()
    data class AddToCart(val product: CartEntity) : ProductListAction()
}
