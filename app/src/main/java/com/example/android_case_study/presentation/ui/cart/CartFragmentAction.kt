package com.example.android_case_study.presentation.ui.cart

sealed class CartFragmentAction {
    //data class GetCartItems(val cartItems: List<CartItem>) : CartFragmentAction()
    data object CompleteShopping: CartFragmentAction()


}