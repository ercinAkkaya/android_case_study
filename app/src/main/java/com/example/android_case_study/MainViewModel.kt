package com.example.android_case_study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_case_study.core.util.manager.SharedStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedStateManager: SharedStateManager
) : ViewModel() {

    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> = _cartItemCount

    private val _favoriteItemCount = MutableStateFlow(0)
    val favoriteItemCount: StateFlow<Int> = _favoriteItemCount

    init {
        viewModelScope.launch {
            sharedStateManager.cartItemCount.collect { count ->
                _cartItemCount.value = count
            }
        }

        viewModelScope.launch {
            sharedStateManager.favoriteItemCount.collect { count ->
                _favoriteItemCount.value = count
            }
        }
    }
}