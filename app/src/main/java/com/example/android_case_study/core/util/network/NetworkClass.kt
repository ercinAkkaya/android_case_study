package com.example.android_case_study.core.util.network

sealed class NetworkResult<T>(val data : T? = null, val message: String? = null){
    class Success<T>(data: T) : NetworkResult<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data = data, message = message)
    class Loading<T>(data:T?=null) : NetworkResult<T>(data = data)
}