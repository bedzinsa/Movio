package com.arunasbedzinskas.movio.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.core.dispatcher.AppDispatchers
import com.arunasbedzinskas.movio.domain.usecase.GetAllMoviesUseCase
import com.arunasbedzinskas.movio.models.Const.EMPTY_STRING
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    private val searchQuery = MutableStateFlow(EMPTY_STRING)

    private val _movies = MutableStateFlow(listOf<CondensedMovieUI>())
    val movies: StateFlow<List<CondensedMovieUI>> = _movies.asStateFlow()

    init {
        getMovies()
    }

    fun searchBy(query: CharSequence?) {
        val normalizedQuery = query?.toString() ?: EMPTY_STRING
        if (normalizedQuery == searchQuery.value) return
        searchQuery.value = normalizedQuery
    }

    @OptIn(FlowPreview::class)
    private fun getMovies() {
        searchQuery
            .debounce(700L)
            .combine(getAllMoviesUseCase()) { searchQuery, movies ->
                filterMovies(searchQuery, movies)
            }
            .onEach { movies -> _movies.value = movies }
            .flowOn(appDispatchers.io)
            .launchIn(viewModelScope)
    }

    private fun filterMovies(
        query: String,
        movies: List<CondensedMovieUI>
    ): List<CondensedMovieUI> {
        if (query.isBlank()) return movies
        return movies.filter {
            it.title.contains(query, ignoreCase = true) || it.releaseYear.contains(query)
        }
    }
}
