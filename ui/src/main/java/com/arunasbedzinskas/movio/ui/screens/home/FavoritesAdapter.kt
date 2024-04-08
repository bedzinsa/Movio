package com.arunasbedzinskas.movio.ui.screens.home

import androidx.recyclerview.widget.DiffUtil
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.models.ui.FavoriteMovieUI
import com.arunasbedzinskas.movio.ui.base.adapter.BaseAdapter

class FavoritesAdapter(favoriteItemClickCallback: (FavoriteMovieUI) -> Unit) : BaseAdapter(
    itemCallback = favoriteItemsCallback,
    favoriteItemClickCallback = favoriteItemClickCallback
) {

    fun submitItems(items: List<FavoriteMovieUI>) {
        val remappedItems = items.map { AdapterItem.FavoriteItem(it) }
        submitList(remappedItems)
    }
}

private val favoriteItemsCallback = object : DiffUtil.ItemCallback<AdapterItem>() {

    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        when {
            oldItem is AdapterItem.FavoriteItem && newItem is AdapterItem.FavoriteItem ->
                oldItem.favoriteMovieUI.id == newItem.favoriteMovieUI.id

            else -> false
        }

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        when {
            oldItem is AdapterItem.FavoriteItem && newItem is AdapterItem.FavoriteItem ->
                oldItem.favoriteMovieUI == newItem.favoriteMovieUI

            else -> false
        }
}
