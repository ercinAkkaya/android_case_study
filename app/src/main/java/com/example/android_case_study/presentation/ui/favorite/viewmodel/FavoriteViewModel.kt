package com.example.android_case_study.presentation.ui.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.core.util.network.Resource
import com.example.android_case_study.data.mapper.toFavoriteItem
import com.example.android_case_study.domain.use_case.favorite.DeleteFavoriteItemUseCase
import com.example.android_case_study.domain.use_case.favorite.GetAllFavoriteItemsUseCase
import com.example.android_case_study.presentation.ui.cart.CartFragmentEffect
import com.example.android_case_study.presentation.ui.cart.CartFragmentState
import com.example.android_case_study.presentation.ui.favorite.FavoriteAction
import com.example.android_case_study.presentation.ui.favorite.FavoriteEffect
import com.example.android_case_study.presentation.ui.favorite.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoriteItemsUseCase: GetAllFavoriteItemsUseCase,
    private val deleteFavoriteItemUseCase: DeleteFavoriteItemUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteState())
    val uiState: StateFlow<FavoriteState> = _uiState

    private val _uiEffect = MutableStateFlow<FavoriteEffect?>(null)
    val uiEffect: StateFlow<FavoriteEffect?> = _uiEffect

    init {
        getAllFavoriteItems()
    }

    fun handleAction(action: FavoriteAction) {
        when (action) {
            is FavoriteAction.DeleteFavoriteItem -> {
                //TODO: Implement deleteFavoriteItem
            }
        }
    }

    private fun getAllFavoriteItems() {
        viewModelScope.launch {
            getAllFavoriteItemsUseCase.getAllFavoriteItems().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = FavoriteState(isLoading = true)
                    }
                    is Resource.Success -> {
                        val items = resource.data?.map { it.toFavoriteItem() } ?: emptyList()
                        _uiState.value = FavoriteState(
                            favoriteItems = items,
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = FavoriteState(
                            isLoading = false,
                            error = resource.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }
}