package com.example.android_case_study.presentation.ui.cart

sealed class CartFragmentEffect {

    data class ShowToast(val message: String) : CartFragmentEffect()

}