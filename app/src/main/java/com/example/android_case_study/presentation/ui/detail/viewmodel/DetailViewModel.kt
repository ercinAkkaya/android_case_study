package com.example.android_case_study.presentation.ui.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android_case_study.presentation.ui.detail.DetailAction
import com.example.android_case_study.presentation.ui.detail.DetailEffect
import com.example.android_case_study.presentation.ui.detail.DetailState
import com.example.android_case_study.presentation.ui.detail.model.DetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    private val _uiEffect = MutableStateFlow<DetailEffect?>(null)
    val uiEffect: StateFlow<DetailEffect?> = _uiEffect

    init {
        savedStateHandle.get<DetailModel>("DetailModel")?.let { detailModel ->
            _uiState.value = _uiState.value.copy(
                productDetailModel = detailModel,
                isFavorite = detailModel.isFavorite
            )
        }
    }

    fun handleAction(action: DetailAction) {
        when (action) {
            is DetailAction.InitializeProduct -> TODO()
            is DetailAction.NavigateBack -> TODO()
            is DetailAction.ToggleFavorite -> TODO()
        }
    }
}
