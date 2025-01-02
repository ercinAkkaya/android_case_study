package com.example.android_case_study.presentation.ui.product_list.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.domain.use_case.home.GetProductsUseCase
import com.example.android_case_study.presentation.ui.product_list.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListState())
    val uiState: StateFlow<ProductListState> = _uiState

    init {
        fetchProducts()
    }

    private var job: Job? = null

    fun refreshProducts() {
        fetchProducts()
    }

    private fun fetchProducts() {
        job?.cancel()
        job = getProductsUseCase.getProducts()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.value = ProductListState(
                            productList = result.data ?: emptyList(),
                            isLoading = false
                        )
                        Log.d("ProductListViewModel", "Success: ${result.data?.firstOrNull()}")
                    }
                    is Resource.Error -> {
                        _uiState.value = ProductListState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _uiState.value = ProductListState(isLoading = true)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}
