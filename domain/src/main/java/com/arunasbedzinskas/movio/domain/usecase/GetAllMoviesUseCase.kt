package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.dataaccess.MoviesDataAccess
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import javax.inject.Inject

fun interface GetAllMoviesUseCase {

    operator fun invoke(): Flow<List<CondensedMovieUI>>
}

internal class GetAllMoviesUseCaseImpl @Inject constructor(
    private val moviesDataAccess: MoviesDataAccess,
    private val localDataStore: LocalDataStore,
    private val mapToCondensedMovieUIUseCase: MapToCondensedMovieUIUseCase
) : GetAllMoviesUseCase {

    override fun invoke(): Flow<List<CondensedMovieUI>> =
        moviesDataAccess.getAll()
            .combineTransform(localDataStore.getFavorites()) { movies, favoriteIds ->
                val mappedMovies = movies.map { movie ->
                    mapToCondensedMovieUIUseCase(
                        movie = movie,
                        isFavorite = favoriteIds.any { it == movie.id }
                    )
                }
                emit(mappedMovies)
            }
}
