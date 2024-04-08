package com.arunasbedzinskas.movio.models.data

data class Movie(
    val id: Long,
    val rating: Double,
    val revenue: Long,
    val releaseDate: String,
    val director: Director,
    val posterUrl: String,
    val cast: List<CastMember>,
    val runtime: Long,
    val title: String,
    val overview: String,
    val reviews: Int,
    val budget: Long,
    val language: Language,
    val genres: List<Genre>
)
