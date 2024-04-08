package com.arunasbedzinskas.movio.models.data

import com.google.gson.annotations.SerializedName

enum class Genre {
    Animation,
    Action,
    Adventure,
    Comedy,
    Crime,
    Drama,
    Fantasy,
    Family,
    History,
    Horror,
    Mystery,
    Music,
    Romance,
    @SerializedName("Science Fiction")
    ScienceFiction,
    Thriller,
    @SerializedName("TV Movie")
    TVMovie,
    War;
}
