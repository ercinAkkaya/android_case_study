package com.example.android_case_study.presentation.ui.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.data.mapper.toCartItem
import com.example.android_case_study.domain.use_case.cart.GetAllCartItemsUseCase
import com.example.android_case_study.presentation.ui.cart.CartFragmentAction
import com.example.android_case_study.presentation.ui.cart.CartFragmentEffect
import com.example.android_case_study.presentation.ui.cart.CartFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartItemsUseCase: GetAllCartItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartFragmentState())
    val uiState: StateFlow<CartFragmentState> = _uiState

    private val _uiEffect = MutableStateFlow<CartFragmentEffect?>(null)
    val uiEffect: StateFlow<CartFragmentEffect?> = _uiEffect
    private var job: Job? = null

    init {
        getAllCart()
    }

    fun handleAction(action: CartFragmentAction) {
        when (action) {
            CartFragmentAction.CompleteShopping -> completeShopping()
        }
    }

    private fun getAllCart() {
        job?.cancel()
        job = viewModelScope.launch {
            getAllCartItemsUseCase.getAllCartItems().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = CartFragmentState(isLoading = true)
                    }
                    is Resource.Success -> {
                        val items = resource.data?.map { it.toCartItem() } ?: emptyList()
                        val totalPrice = items.sumOf { it.price.toDouble() }
                        _uiState.value = CartFragmentState(
                            cartItems = items,
                            isLoading = false,
                            totalPrice = totalPrice
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = CartFragmentState(
                            isLoading = false,
                            error = resource.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }

    private fun completeShopping() {
        _uiEffect.tryEmit(CartFragmentEffect.ShowToast("Shopping completed"))
    }
}
