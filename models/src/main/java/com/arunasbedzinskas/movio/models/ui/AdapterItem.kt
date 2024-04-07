package com.arunasbedzinskas.movio.models.ui

import android.text.SpannableString

sealed class AdapterItem(val viewType: Int) {

    class UserItem(val userDataUI: UserDataUI) : AdapterItem(TYPE_USER)

    class LabelItem(val labelText: SpannableString) : AdapterItem(TYPE_LABEL)

    class MovieItem(val condensedMovieUI: CondensedMovieUI) : AdapterItem(TYPE_MOVIE)

    class FavoritesListItem(val favoritesUI: FavoritesUI) : AdapterItem(TYPE_FAVORITES_LIST)

    class FavoriteItem(val favoriteMovieUI: FavoriteMovieUI) : AdapterItem(TYPE_FAVORITE)

    companion object {
        const val TYPE_USER = 1
        const val TYPE_LABEL = 2
        const val TYPE_MOVIE = 3
        const val TYPE_FAVORITES_LIST = 4
        const val TYPE_FAVORITE = 5
    }
}
