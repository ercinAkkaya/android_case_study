package com.example.android_case_study.presentation.ui.detail

sealed class DetailEffect {
    data class ShowError(val message: String) : DetailEffect()
    data class ShowToastMessage(val message: String) : DetailEffect()
    data object NavigateBack : DetailEffect()
}
