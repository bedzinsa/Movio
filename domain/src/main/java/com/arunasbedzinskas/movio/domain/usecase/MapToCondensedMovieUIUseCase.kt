package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.core.ext.round
import com.arunasbedzinskas.movio.core.ext.toYear
import com.arunasbedzinskas.movio.models.data.Movie
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import java.math.RoundingMode
import javax.inject.Inject

fun interface MapToCondensedMovieUIUseCase {

    operator fun invoke(movie: Movie, isFavorite: Boolean): CondensedMovieUI
}

internal class MapToCondensedMovieUIUseCaseImpl @Inject constructor() :
    MapToCondensedMovieUIUseCase {

    override fun invoke(movie: Movie, isFavorite: Boolean): CondensedMovieUI =
        CondensedMovieUI(
            id = movie.id,
            title = movie.title,
            releaseYear = movie.releaseDate.toYear(),
            rating = movie.rating.round(decimals = 0, RoundingMode.FLOOR).toInt(),
            logoUrl = movie.posterUrl,
            isFavorite = isFavorite
        )
}
