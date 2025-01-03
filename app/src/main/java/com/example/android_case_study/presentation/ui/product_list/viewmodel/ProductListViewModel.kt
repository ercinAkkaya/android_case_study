package com.example.android_case_study.presentation.ui.product_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.MainViewModel
import com.example.android_case_study.core.util.manager.SharedStateManager
import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.domain.use_case.cart.GetCartItemCountUseCase
import com.example.android_case_study.domain.use_case.cart.InsertCartItemUseCase
import com.example.android_case_study.domain.use_case.favorite.AddToFavoritesUseCase
import com.example.android_case_study.domain.use_case.favorite.DeleteFavoriteItemUseCase
import com.example.android_case_study.domain.use_case.favorite.GetFavoriteItemCountUseCase
import com.example.android_case_study.domain.use_case.home.GetProductsUseCase
import com.example.android_case_study.presentation.ui.detail.model.DetailModel
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
    private val getProductsUseCase: GetProductsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val addToCartUseCase: InsertCartItemUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteItemUseCase,
    private val sharedStateManager: SharedStateManager,
    private val getCartItemCountUseCase: GetCartItemCountUseCase,
    private val getFavoriteItemCountUseCase: GetFavoriteItemCountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListState())
    val uiState: StateFlow<ProductListState> = _uiState

    private val _uiEffect = MutableStateFlow<ProductListEffect?>(null)
    val uiEffect: StateFlow<ProductListEffect?> = _uiEffect

    private var originalList: List<Product> = emptyList()
    private var job: Job? = null

    init {
        fetchProducts()
        updateBadgesCount()
    }

    fun handleAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.OnInputChange -> updateList(action.input)
            is ProductListAction.OnFilter -> applyPriceFilter(action.minPrice)
            is ProductListAction.ToggleBottomSheet -> toggleBottomSheet()
            is ProductListAction.Refresh -> refreshData()
            is ProductListAction.AddFavorite -> addFavorite(action.product)
            is ProductListAction.DeleteFavorite -> deleteFavorite(action.id, action.name)
            is ProductListAction.NavigateToDetail -> navigateToDetail(action.productDetail)
            is ProductListAction.AddToCart -> addToCart(action.product)
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

    private fun addFavorite(favoriteItem: FavoriteEntity) {
        viewModelScope.launch {
            addToFavoritesUseCase.execute(favoriteItem)
            sharedStateManager.incrementFavoriteItemCount()
        }
        _uiEffect.tryEmit(ProductListEffect.ShowToastMessage("Added to favorites: ${favoriteItem.name}"))
    }

    private fun addToCart(cartItem: CartEntity) {
        viewModelScope.launch {
            addToCartUseCase.invoke(cartItem)
            sharedStateManager.incrementCartItemCount()
        }
        _uiEffect.tryEmit(ProductListEffect.ShowToastMessage("Added to cart: ${cartItem.name}"))
    }

    private fun deleteFavorite(id: String, name: String) {
        viewModelScope.launch {
            deleteFavoriteUseCase.deleteFavoriteItem(id)
            sharedStateManager.decrementFavoriteItemCount()
        }
        _uiEffect.tryEmit(ProductListEffect.ShowToastMessage("Removed from favorites: $name"))
    }

    private fun navigateToDetail(product: DetailModel) {
        _uiEffect.tryEmit(ProductListEffect.NavigateToDetail(product))
    }

    private fun updateBadgesCount() {
        viewModelScope.launch {
            sharedStateManager.updateCartItemCount(getCartItemCountUseCase.invoke())
        }

        viewModelScope.launch {
            sharedStateManager.updateFavoriteItemCount(getFavoriteItemCountUseCase.invoke())
        }
    }

}
