package com.arunasbedzinskas.movio.models.ui

data class CondensedMovieUI(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val rating: Int,
    val logoUrl: String,
    val isFavorite: Boolean
)
