package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.dataaccess.StaffPicksDataAccess
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.roundToInt

fun interface GetStaffPickMoviesUseCase {

    operator fun invoke(): Flow<List<CondensedMovieUI>>
}

internal class GetStaffPickMoviesUseCaseImpl @Inject constructor(
    private val staffPicksDataAccess: StaffPicksDataAccess,
    private val localDataStore: LocalDataStore
) : GetStaffPickMoviesUseCase {

    override fun invoke(): Flow<List<CondensedMovieUI>> =
        staffPicksDataAccess.getAll()
            .combineTransform(localDataStore.getFavorites()) { movies, favoriteIds ->
                val moviesUIs = movies.map { movie ->
                    CondensedMovieUI(
                        id = movie.id,
                        title = movie.title,
                        releaseYear = LocalDate.parse(
                            movie.releaseDate,
                            DateTimeFormatter.ISO_DATE
                        ).year.toString(),
                        rating = movie.rating.roundToInt(),
                        logoUrl = movie.posterUrl,
                        isFavorite = favoriteIds.any { it == movie.id }
                    )
                }
                emit(moviesUIs)
            }
}
