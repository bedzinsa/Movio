package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.dataaccess.StaffPicksDataAccess
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import javax.inject.Inject

fun interface GetStaffPickMoviesUseCase {

    operator fun invoke(): Flow<List<CondensedMovieUI>>
}

internal class GetStaffPickMoviesUseCaseImpl @Inject constructor(
    private val staffPicksDataAccess: StaffPicksDataAccess,
    private val localDataStore: LocalDataStore,
    private val mapToCondensedMovieUIUseCase: MapToCondensedMovieUIUseCase
) : GetStaffPickMoviesUseCase {

    override fun invoke(): Flow<List<CondensedMovieUI>> =
        staffPicksDataAccess.getAll()
            .combineTransform(localDataStore.getFavorites()) { movies, favoriteIds ->
                val moviesUIs = movies.map { movie ->
                    mapToCondensedMovieUIUseCase(
                        movie = movie,
                        isFavorite = favoriteIds.any { it == movie.id }
                    )
                }
                emit(moviesUIs)
            }
}
