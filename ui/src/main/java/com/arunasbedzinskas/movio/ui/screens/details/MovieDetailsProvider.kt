package com.arunasbedzinskas.movio.ui.screens.details

import android.content.Context
import com.arunasbedzinskas.movio.models.data.Genre
import com.arunasbedzinskas.movio.models.data.KeyFact
import com.arunasbedzinskas.movio.models.data.Language
import com.arunasbedzinskas.movio.ui.R
import javax.inject.Inject

class MovieDetailsProvider @Inject constructor(private val appContext: Context) {

    fun getGenre(genre: Genre) = when (genre) {
        Genre.Animation -> appContext.getString(R.string.genre_animation)
        Genre.Action -> appContext.getString(R.string.genre_action)
        Genre.Adventure -> appContext.getString(R.string.genre_adventure)
        Genre.Comedy -> appContext.getString(R.string.genre_comedy)
        Genre.Crime -> appContext.getString(R.string.genre_crime)
        Genre.Drama -> appContext.getString(R.string.genre_drama)
        Genre.Fantasy -> appContext.getString(R.string.genre_fantasy)
        Genre.Family -> appContext.getString(R.string.genre_family)
        Genre.History -> appContext.getString(R.string.genre_history)
        Genre.Horror -> appContext.getString(R.string.genre_horror)
        Genre.Mystery -> appContext.getString(R.string.genre_mystery)
        Genre.Music -> appContext.getString(R.string.genre_music)
        Genre.Romance -> appContext.getString(R.string.genre_romance)
        Genre.ScienceFiction -> appContext.getString(R.string.genre_science_fiction)
        Genre.Thriller -> appContext.getString(R.string.genre_thriller)
        Genre.TVMovie -> appContext.getString(R.string.genre_tv_movie)
        Genre.War -> appContext.getString(R.string.genre_war)
    }

    fun getKeyFactTitle(keyFact: KeyFact) = when(keyFact) {
        KeyFact.Budget -> appContext.getString(R.string.key_fact_budget)
        KeyFact.Revenue -> appContext.getString(R.string.key_fact_revenue)
        KeyFact.OriginalLanguage -> appContext.getString(R.string.key_fact_language)
        KeyFact.Rating -> appContext.getString(R.string.key_fact_rating)
    }

    fun getLanguage(language: String) = when (Language.valueOf(language)) {
        Language.en -> appContext.getString(R.string.language_en)
        Language.ja -> appContext.getString(R.string.language_ja)
        Language.ko -> appContext.getString(R.string.language_ko)
        Language.fr -> appContext.getString(R.string.language_fr)
    }
}
