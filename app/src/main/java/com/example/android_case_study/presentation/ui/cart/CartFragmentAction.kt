package com.example.android_case_study.presentation.ui.cart

sealed class CartFragmentAction {
    data object CompleteShopping : CartFragmentAction()
}
