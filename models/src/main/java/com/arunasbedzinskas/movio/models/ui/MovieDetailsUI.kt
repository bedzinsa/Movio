package com.arunasbedzinskas.movio.models.ui

import com.arunasbedzinskas.movio.models.data.Genre

data class MovieDetailsUI(
    val id: Long,
    val isFavorite: Boolean,
    val logo: String,
    val rating: Int,
    val releaseDate: String,
    val duration: String,
    val title: String,
    val releaseYear: String,
    val genres: List<Genre>,
    val overview: String,
    val keyFacts: List<KeyFactUI>
)
