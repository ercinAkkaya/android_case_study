package com.example.android_case_study.core.util.manager

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedStateManager @Inject constructor() {

    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> = _cartItemCount

    private val _favoriteItemCount = MutableStateFlow(0)
    val favoriteItemCount: StateFlow<Int> = _favoriteItemCount

    fun updateCartItemCount(count: Int) {
        _cartItemCount.value = count
    }

    fun updateFavoriteItemCount(count: Int) {
        _favoriteItemCount.value = count
    }

    fun incrementCartItemCount() {
        _cartItemCount.value += 1
    }

    fun incrementFavoriteItemCount() {
        _favoriteItemCount.value += 1
    }

    fun decrementCartItemCount() {
        _cartItemCount.value -= 1
    }

    fun decrementFavoriteItemCount() {
        _favoriteItemCount.value -= 1
    }
}
