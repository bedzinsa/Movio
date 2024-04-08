package com.arunasbedzinskas.movio.ui.screens.search

import androidx.recyclerview.widget.DiffUtil
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import com.arunasbedzinskas.movio.ui.base.adapter.BaseAdapter
import com.arunasbedzinskas.movio.ui.callbacks.MovieClickCallback

class SearchMoviesAdapter(
    movieClickCallback: MovieClickCallback
) : BaseAdapter(
    itemCallback = searchMoviesItemCallback,
    movieClickCallback = movieClickCallback
) {

    fun submitItems(items: List<CondensedMovieUI>) {
        val remappedItems = items.map { AdapterItem.MovieItem(it) }
        submitList(remappedItems)
    }
}

private val searchMoviesItemCallback = object : DiffUtil.ItemCallback<AdapterItem>() {

    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        when {
            oldItem is AdapterItem.MovieItem && newItem is AdapterItem.MovieItem ->
                oldItem.condensedMovieUI.id == newItem.condensedMovieUI.id

            else -> false
        }

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        when {
            oldItem is AdapterItem.MovieItem && newItem is AdapterItem.MovieItem ->
                oldItem.condensedMovieUI == newItem.condensedMovieUI

            else -> false
        }
}
