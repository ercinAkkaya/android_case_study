package com.example.android_case_study.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun launchDataLoad(block: suspend () -> Unit) {
        setLoading(true)
        viewModelScope.launch {
            try {
                block()
            } catch (error: Exception) {
                setErrorMessage(error.message.toString())
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }
}
