package com.arunasbedzinskas.movio.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.domain.usecase.ChangeFavoriteMovieUseCase
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val changeFavoriteMovieUseCase: ChangeFavoriteMovieUseCase
) : ViewModel() {

    fun onFavoriteChanged(movieId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            changeFavoriteMovieUseCase(movieId, isFavorite)
        }
    }
}
