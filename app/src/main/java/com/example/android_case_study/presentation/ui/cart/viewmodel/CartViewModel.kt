package com.example.android_case_study.presentation.ui.cart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.data.mapper.toCartItem
import com.example.android_case_study.domain.use_case.cart.GetAllCartItemsUseCase
import com.example.android_case_study.presentation.ui.cart.CartFragmentAction
import com.example.android_case_study.presentation.ui.cart.CartFragmentEffect
import com.example.android_case_study.presentation.ui.cart.CartFragmentState
import com.example.android_case_study.presentation.ui.product_list.ProductListEffect
import com.example.android_case_study.presentation.ui.product_list.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(

    private val getAllCartItemsUseCase: GetAllCartItemsUseCase

) : ViewModel(){

    private val _uiState = MutableStateFlow(CartFragmentState())
    val uiState: StateFlow<CartFragmentState> = _uiState

    private val _uiEffect = MutableStateFlow<CartFragmentEffect?>(null)
    val uiEffect: StateFlow<CartFragmentEffect?> = _uiEffect

    init {

        getAllCart()

    }

    fun handleAction(action: CartFragmentAction) {
        when (action) {
            //TODO implement action handling
            CartFragmentAction.CompleteShopping -> TODO()
        }
    }


    private fun getAllCart() {
        viewModelScope.launch {
            val items = getAllCartItemsUseCase().map { it.toCartItem() }
            _uiState.value = _uiState.value.copy(cartItems = items)
            items.forEach { item ->
                Log.d("CartViewModel", "CartItem: $item")
            }
        }
    }

    //TODO delete all cart yapılacak daodan
    private fun completeShopping(){
        _uiEffect.tryEmit(CartFragmentEffect.ShowToast("Shopping completed"))
    }

}