package com.arunasbedzinskas.movio.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.core.dispatcher.AppDispatchers
import com.arunasbedzinskas.movio.domain.usecase.GetMovieByIdUseCase
import com.arunasbedzinskas.movio.models.state.UiState
import com.arunasbedzinskas.movio.models.ui.MovieDetailsUI
import com.arunasbedzinskas.movio.ui.screens.details.MovieDetailsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val dispatchers: AppDispatchers,
    val movieDetailsProvider: MovieDetailsProvider
) : ViewModel() {

    private val _movieData = MutableStateFlow<UiState<MovieDetailsUI>>(UiState.LoadingState())
    val movieData: StateFlow<UiState<MovieDetailsUI>> = _movieData.asStateFlow()

    fun initWithMovieId(movieId: Long) {
        getMovieByIdUseCase(movieId)
            .flowOn(dispatchers.io)
            .onEach { movie -> _movieData.value = UiState.NormalState(movie) }
            .launchIn(viewModelScope)
    }
}
