package com.arunasbedzinskas.movio.ui.callbacks

import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI

interface MovieClickCallback {

    fun onItemClick(item: CondensedMovieUI)

    fun onFavoriteClick(item: CondensedMovieUI)
}
