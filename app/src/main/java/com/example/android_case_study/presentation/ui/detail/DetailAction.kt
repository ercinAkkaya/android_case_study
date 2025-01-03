package com.example.android_case_study.presentation.ui.detail

import com.example.android_case_study.presentation.ui.detail.model.DetailModel

sealed class DetailAction {
    data class InitializeProduct(val detailModel: DetailModel) : DetailAction()
    data object ToggleFavorite : DetailAction()
    data object NavigateBack : DetailAction()
}
