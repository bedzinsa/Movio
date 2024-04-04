package com.arunasbedzinskas.movio.models.state

sealed class UiState<T> {

    class LoadingState<T> : UiState<T>()

    class NormalState<T>(val data: T) : UiState<T>()

    class ErrorState<T>(val error: String) : UiState<T>()
}
