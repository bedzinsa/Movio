package com.arunasbedzinskas.movio.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.domain.usecase.ChangeFavoriteMovieUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetFavoriteMoviesUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetStaffPickMoviesUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetUserDataUseCase
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val getStaffPickMoviesUseCase: GetStaffPickMoviesUseCase,
    private val changeFavoriteMovieUseCase: ChangeFavoriteMovieUseCase,
    private val homeProvider: HomeProvider
) : ViewModel() {

    private val _homeListUI = MutableLiveData<List<Any>>()
    val homeListUI: LiveData<List<Any>> = _homeListUI

    init {
        getData()
    }

    fun onFavoriteChanged(condensedMovieUI: CondensedMovieUI) {
        viewModelScope.launch {
            changeFavoriteMovieUseCase(condensedMovieUI.id, condensedMovieUI.isFavorite)
        }
    }

    private fun getData() {
        combine(
            getUserDataUseCase(),
            getFavoriteMoviesUseCase(),
            getStaffPickMoviesUseCase()
        ) { userData, favorites, staffPickMovies ->
                with(mutableListOf<Any>()) {
                    add(userData)
                    if (favorites.favoriteUIs.isNotEmpty()) {
                        add(homeProvider.getFavoritesLabel())
                        add(favorites)
                    }
                    add(homeProvider.getStaffPicksLabel())
                    addAll(staffPickMovies)
                    toList()
                }
            }
            .onEach { movies -> _homeListUI.value = movies }
            .launchIn(viewModelScope)

    }

    companion object {

        private const val TAG = "HomeViewModel"
    }
}
