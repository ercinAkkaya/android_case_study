package com.example.android_case_study.presentation.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android_case_study.presentation.ui.detail.DetailState
import com.example.android_case_study.presentation.ui.detail.model.DetailModel
import com.example.android_case_study.presentation.ui.product_list.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    fun setProductDetail(detailModel: DetailModel) {
        _uiState.value = _uiState.value.copy(productDetailModel = detailModel)
    }
}