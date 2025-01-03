package com.example.android_case_study.presentation.ui.cart

import com.example.android_case_study.domain.model.CartItem

data class CartFragmentState(
    val cartItems: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val isLoading: Boolean = false,
    val error: String = ""
)
