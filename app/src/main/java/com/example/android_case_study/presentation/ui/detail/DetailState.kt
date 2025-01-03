package com.example.android_case_study.presentation.ui.detail

import com.example.android_case_study.presentation.ui.detail.model.DetailModel

data class DetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val productDetailModel: DetailModel? = null,
    val isFavorite: Boolean = false
)
