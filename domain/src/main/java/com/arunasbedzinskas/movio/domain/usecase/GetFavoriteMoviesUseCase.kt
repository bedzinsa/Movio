package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.dataaccess.MoviesDataAccess
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.ui.FavoriteMovieUI
import com.arunasbedzinskas.movio.models.ui.FavoritesUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import javax.inject.Inject

fun interface GetFavoriteMoviesUseCase {

    operator fun invoke(): Flow<FavoritesUI>
}

internal class GetFavoriteMoviesUseCaseImpl @Inject constructor(
    private val moviesDataAccess: MoviesDataAccess,
    private val localDataStore: LocalDataStore
) : GetFavoriteMoviesUseCase {

    override fun invoke(): Flow<FavoritesUI> =
        moviesDataAccess.getAll()
            .combineTransform(localDataStore.getFavorites()) { movies, favoriteIds ->
                val favoriteMovies = movies.filter { movie ->
                    favoriteIds.any { it == movie.id }
                }.map { FavoriteMovieUI(it.id, it.posterUrl) }
                emit(FavoritesUI(favoriteMovies))
            }
}