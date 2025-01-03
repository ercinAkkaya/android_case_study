package com.example.android_case_study.presentation.ui.favorite

import com.example.android_case_study.domain.model.FavoriteItem

data class FavoriteState(
    val favoriteItems: List<FavoriteItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
