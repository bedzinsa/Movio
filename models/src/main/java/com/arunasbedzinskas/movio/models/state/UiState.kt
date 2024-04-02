package com.arunasbedzinskas.movio.models.state

sealed class UiState<T> {

    class LoadingState<T> : UiState<T>()

    class NormalState<T>(val data: T) : UiState<T>()
}
