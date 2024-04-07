package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.models.data.Movie
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.roundToInt

fun interface MapToCondensedMovieUIUseCase {

    operator fun invoke(movie: Movie, isFavorite: Boolean): CondensedMovieUI
}

internal class MapToCondensedMovieUIUseCaseImpl @Inject constructor() :
    MapToCondensedMovieUIUseCase {

    override fun invoke(movie: Movie, isFavorite: Boolean): CondensedMovieUI =
        CondensedMovieUI(
            id = movie.id,
            title = movie.title,
            releaseYear = LocalDate.parse(
                movie.releaseDate,
                DateTimeFormatter.ISO_DATE
            ).year.toString(),
            rating = movie.rating.roundToInt(),
            logoUrl = movie.posterUrl,
            isFavorite = isFavorite
        )
}
