package com.example.android_case_study.presentation.ui.favorite

sealed class FavoriteAction {

    data class DeleteFavoriteItem(val id: String, val name: String) : FavoriteAction()

}