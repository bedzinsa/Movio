package com.arunasbedzinskas.movio.domain.usecase

import android.util.Log
import com.arunasbedzinskas.movio.core.ext.formatToCurrencyAmount
import com.arunasbedzinskas.movio.core.ext.round
import com.arunasbedzinskas.movio.core.ext.toHoursMinutes
import com.arunasbedzinskas.movio.core.ext.toYear
import com.arunasbedzinskas.movio.data.dataaccess.MoviesDataAccess
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.Const.SYMBOL_DOLLAR
import com.arunasbedzinskas.movio.models.data.KeyFact
import com.arunasbedzinskas.movio.models.data.Movie
import com.arunasbedzinskas.movio.models.ui.KeyFactUI
import com.arunasbedzinskas.movio.models.ui.MovieDetailsUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

fun interface GetMovieByIdUseCase {

    operator fun invoke(movieId: Long): Flow<MovieDetailsUI>
}

internal class GetMovieByIdUseCaseImpl @Inject constructor(
    private val moviesDataAccess: MoviesDataAccess,
    private val localDataStore: LocalDataStore
) : GetMovieByIdUseCase {

    override fun invoke(movieId: Long): Flow<MovieDetailsUI> =
        moviesDataAccess.getAll()
            .combineTransform(localDataStore.getFavorites()) { movies, favorites ->
                val movie = movies.firstOrNull { it.id == movieId }
                movie?.let {
                    emit(mapToMovieDetailsUI(movie, favorites))
                } ?: Log.e(TAG, "Unable to find movie with id $movieId")
            }

    private fun mapToMovieDetailsUI(movie: Movie, favorites: List<Long>) = MovieDetailsUI(
        id = movie.id,
        isFavorite = favorites.any { it == movie.id },
        logo = movie.posterUrl,
        rating = movie.rating.round(decimals = 0, roundingMode = RoundingMode.FLOOR).toInt(),
        releaseDate = LocalDate.parse(movie.releaseDate).format(DateTimeFormatter.ofPattern("dd.MM.YYY")),
        duration = movie.runtime.minutes.toHoursMinutes(),
        title = movie.title,
        releaseYear = "(${movie.releaseDate.toYear()})",
        genres = movie.genres,
        overview = movie.overview,
        keyFacts = listOf(
            KeyFactUI(KeyFact.Budget, "${SYMBOL_DOLLAR}${movie.budget.formatToCurrencyAmount()}"),
            KeyFactUI(KeyFact.Revenue, "${SYMBOL_DOLLAR}${movie.revenue.formatToCurrencyAmount()}"),
            KeyFactUI(KeyFact.OriginalLanguage, movie.language.name),
            KeyFactUI(KeyFact.Rating, buildString {
                append(movie.rating.round())
                append(" (${movie.reviews})")
            })
        )
    )

    companion object {

        private const val TAG = "GetMovieByIdUseCase"
    }
}
