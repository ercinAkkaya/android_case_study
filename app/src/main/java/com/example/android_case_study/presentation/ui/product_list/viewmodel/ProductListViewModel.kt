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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(ProductListState())
    val uiState = _uiState

    init {
        fetchAndLogProducts()
    }

    private var job: Job? = null

    private fun fetchAndLogProducts() {
        job?.cancel()
        job = getProductsUseCase.getProducts().onEach {
            when (it) {
                is Resource.Success -> {
                    _uiState.value =
                        ProductListState(productList = it.data ?: emptyList(), isLoading = false)
                    Log.d("ProductListViewModel", "Products: ${uiState.value.productList.first()}")
                }

                is Resource.Error -> {
                    _uiState.value =
                        ProductListState(error = it.message ?: "Error", isLoading = false)
                }

                is Resource.Loading -> {
                    _uiState.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}