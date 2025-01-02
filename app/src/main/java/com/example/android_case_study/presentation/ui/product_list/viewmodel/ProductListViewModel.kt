package com.example.android_case_study.presentation.ui.product_list.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.domain.use_case.home.GetProductsUseCase
import com.example.android_case_study.presentation.ui.product_list.ProductListAction
import com.example.android_case_study.presentation.ui.product_list.ProductListEffect
import com.example.android_case_study.presentation.ui.product_list.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListState())
    val uiState: StateFlow<ProductListState> = _uiState

    private val _uiEffect = MutableStateFlow<ProductListEffect?>(null)
    val uiEffect: StateFlow<ProductListEffect?> = _uiEffect

    private var originalList: List<Product> = emptyList()
    private var job: Job? = null

    init {
        fetchProducts()
    }


    fun handleAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.OnInputChange -> updateList(action.input)
            is ProductListAction.OnFilter -> applyPriceFilter(action.minPrice)
            is ProductListAction.ToggleBottomSheet -> toggleBottomSheet()
            is ProductListAction.Refresh -> refreshData()
            is ProductListAction.AddFavorite -> addFavorite(action.productName)
            is ProductListAction.DeleteFavorite -> deleteFavorite(action.productName)
        }
    }

    private fun toggleBottomSheet() {
        _uiState.value = _uiState.value.copy(
            bottomSheetVisible = !_uiState.value.bottomSheetVisible
        )
    }

    private fun updateList(input: String) {
        if (input.isBlank()) {
            _uiState.value = _uiState.value.copy(productList = originalList)
        } else {
            val filteredList = originalList.filter {
                it.name.contains(input, ignoreCase = true)
            }
            _uiState.value = _uiState.value.copy(productList = filteredList)
        }
    }

    private fun applyPriceFilter(minPrice: Double) {
        val filteredList = originalList.filter { product ->
            product.price >= minPrice.toString()
        }
        _uiState.value = _uiState.value.copy(
            productList = filteredList,
            bottomSheetVisible = false
        )
    }

    private fun fetchProducts() {
        job?.cancel()
        job = getProductsUseCase.getProducts()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        originalList = result.data ?: emptyList()
                        _uiState.value = ProductListState(
                            productList = originalList,
                            isLoading = false
                        )
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

    private fun refreshData() {
        fetchProducts()
    }

    //TODO: Implement API request functions
    private fun addFavorite(productName: String) {
        // API request function, currently empty
        _uiEffect.tryEmit(ProductListEffect.ShowToastMessage("Added to favorites: $productName"))
    }

    private fun deleteFavorite(productName: String) {
        // API request function, currently empty
        _uiEffect.tryEmit(ProductListEffect.ShowToastMessage("Removed from favorites: $productName"))
    }

}
