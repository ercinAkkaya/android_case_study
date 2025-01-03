package com.example.android_case_study.presentation.ui.favorite

sealed class FavoriteEffect {

    data class ShowToast(val message: String) : FavoriteEffect()

}