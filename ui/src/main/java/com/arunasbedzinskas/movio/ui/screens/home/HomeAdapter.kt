package com.arunasbedzinskas.movio.ui.screens.home

import android.text.SpannableString
import androidx.recyclerview.widget.DiffUtil
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import com.arunasbedzinskas.movio.models.ui.FavoriteMovieUI
import com.arunasbedzinskas.movio.models.ui.FavoritesUI
import com.arunasbedzinskas.movio.models.ui.UserDataUI
import com.arunasbedzinskas.movio.ui.base.adapter.BaseAdapter
import com.arunasbedzinskas.movio.ui.callbacks.MovieClickCallback

class HomeAdapter(
    movieClickCallback: MovieClickCallback,
    searchClickCallback: () -> Unit,
    favoriteItemClickCallback: (FavoriteMovieUI) -> Unit
) : BaseAdapter(
    itemCallback = homeItemsCallback,
    movieClickCallback = movieClickCallback,
    searchClickCallback = searchClickCallback,
    favoriteItemClickCallback = favoriteItemClickCallback
) {

    fun submitItems(items: List<Any>) {
        val remapped: List<AdapterItem> = items.mapNotNull {
            when (it) {
                is SpannableString -> AdapterItem.LabelItem(it)
                is CondensedMovieUI -> AdapterItem.MovieItem(it)
                is UserDataUI -> AdapterItem.UserItem(it)
                is FavoritesUI -> AdapterItem.FavoritesListItem(it)
                is FavoriteMovieUI -> AdapterItem.FavoriteItem(it)
                else -> null
            }
        }
        submitList(remapped)
    }
}

private val homeItemsCallback = object : DiffUtil.ItemCallback<AdapterItem>() {

    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        when {
            oldItem is AdapterItem.MovieItem && newItem is AdapterItem.MovieItem ->
                oldItem.condensedMovieUI.id == newItem.condensedMovieUI.id

            (oldItem is AdapterItem.LabelItem && newItem is AdapterItem.LabelItem)
                    || (oldItem is AdapterItem.UserItem && newItem is AdapterItem.UserItem) ->
                areContentsTheSame(oldItem, newItem)

            else -> false
        }

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        when {
            oldItem is AdapterItem.MovieItem && newItem is AdapterItem.MovieItem ->
                oldItem.condensedMovieUI == newItem.condensedMovieUI

            oldItem is AdapterItem.LabelItem && newItem is AdapterItem.LabelItem ->
                oldItem.labelText == newItem.labelText

            oldItem is AdapterItem.UserItem && newItem is AdapterItem.UserItem ->
                oldItem.userDataUI == newItem.userDataUI

            else -> false
        }
}
