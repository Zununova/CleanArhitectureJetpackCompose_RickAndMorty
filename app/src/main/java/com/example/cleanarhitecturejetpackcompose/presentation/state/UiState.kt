package com.example.cleanarhitecturejetpackcompose.presentation.state

import com.example.cleanarhitecturejetpackcompose.domain.models.Result

sealed class UiState<T> {

    class Loading<T>() : UiState<T>()
    class Error<T>(val message: String) : UiState<T>()
    class Success<T>(val data: List<Result>) : UiState<T>()
}