package com.arunasbedzinskas.movio.ui.viewholders

import android.content.res.ColorStateList
import coil.load
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import com.arunasbedzinskas.movio.ui.R
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder
import com.arunasbedzinskas.movio.ui.databinding.HolderMovieBinding

class MovieViewHolder(val binding: HolderMovieBinding) : BaseViewHolder(binding) {

    init {
        binding.cbMovieFavorite.buttonIconTintList = ColorStateList(arrayOf(), intArrayOf())
    }

    override fun onBind(item: AdapterItem) {
        if (item !is AdapterItem.MovieItem) return
        setupUI(item.condensedMovieUI)
    }

    private fun setupUI(condensedMovieUI: CondensedMovieUI) {
        with(binding) {
            ivMovieLogo.load(condensedMovieUI.logoUrl)
            tvMovieReleaseYear.text = condensedMovieUI.releaseYear
            tvMovieTitle.text = condensedMovieUI.title
            tvMovieRating.text = binding.root.context.getString(
                R.string.home_movie_rating_text, condensedMovieUI.rating.toString()
            )
            cbMovieFavorite.isChecked = condensedMovieUI.isFavorite
        }
    }
}
