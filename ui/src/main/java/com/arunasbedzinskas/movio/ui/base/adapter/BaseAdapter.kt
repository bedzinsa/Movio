package com.arunasbedzinskas.movio.ui.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.models.ui.FavoriteMovieUI
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder
import com.arunasbedzinskas.movio.ui.callbacks.MovieClickCallback
import com.arunasbedzinskas.movio.ui.databinding.HolderFavoriteBinding
import com.arunasbedzinskas.movio.ui.databinding.HolderFavoriteListBinding
import com.arunasbedzinskas.movio.ui.databinding.HolderLabelBinding
import com.arunasbedzinskas.movio.ui.databinding.HolderMovieBinding
import com.arunasbedzinskas.movio.ui.databinding.HolderUserBinding
import com.arunasbedzinskas.movio.ui.screens.home.FavoritesAdapter
import com.arunasbedzinskas.movio.ui.viewholders.FavoriteViewHolder
import com.arunasbedzinskas.movio.ui.viewholders.FavoritesListViewHolder
import com.arunasbedzinskas.movio.ui.viewholders.LabelViewHolder
import com.arunasbedzinskas.movio.ui.viewholders.MovieViewHolder
import com.arunasbedzinskas.movio.ui.viewholders.UserViewHolder

abstract class BaseAdapter(
    itemCallback: DiffUtil.ItemCallback<AdapterItem>,
    private val movieClickCallback: MovieClickCallback? = null,
    private val searchClickCallback: (() -> Unit)? = null,
    private val favoriteItemClickCallback: ((FavoriteMovieUI) -> Unit)? = null
) : AutoBindAdapter(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        with(LayoutInflater.from(parent.context)) {
            when (viewType) {
                AdapterItem.TYPE_MOVIE -> MovieViewHolder(
                    HolderMovieBinding.inflate(this, parent, false)
                )

                AdapterItem.TYPE_LABEL -> LabelViewHolder(
                    HolderLabelBinding.inflate(this, parent, false)
                )

                AdapterItem.TYPE_USER -> UserViewHolder(
                    HolderUserBinding.inflate(this, parent, false)
                )

                AdapterItem.TYPE_FAVORITES_LIST -> FavoritesListViewHolder(
                    HolderFavoriteListBinding.inflate(this, parent, false)
                )

                AdapterItem.TYPE_FAVORITE -> FavoriteViewHolder(
                    HolderFavoriteBinding.inflate(this, parent, false)
                )
                else -> throw Exception("Invalid viewType: $viewType")
            }
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> bindMovieClickListener(
                holder.binding,
                getItem(position) as AdapterItem.MovieItem
            )

            is UserViewHolder -> bindUserClickListener(holder.binding)
            is FavoritesListViewHolder -> bindFavoritesListAdapter(holder.binding)
            is FavoriteViewHolder -> bindFavoriteClickListener(
                holder.binding,
                getItem(position) as AdapterItem.FavoriteItem
            )
        }
        super.onBindViewHolder(holder, position)
    }

    private fun bindMovieClickListener(binding: HolderMovieBinding, item: AdapterItem.MovieItem) =
        with(binding) {
            root.setOnClickListener {
                movieClickCallback?.onItemClick(item.condensedMovieUI)
            }
            cbMovieFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
                if (!buttonView.isPressed) return@setOnCheckedChangeListener
                movieClickCallback?.onFavoriteClick(item.condensedMovieUI.copy(isFavorite = isChecked))
            }
        }

    private fun bindUserClickListener(binding: HolderUserBinding) =
        binding.cvHeaderSearch.setOnClickListener {
            searchClickCallback?.invoke()
        }

    private fun bindFavoritesListAdapter(binding: HolderFavoriteListBinding) {
        binding.rvFavorites.adapter = FavoritesAdapter { favoriteItemClickCallback?.invoke(it) }
    }

    private fun bindFavoriteClickListener(
        binding: HolderFavoriteBinding,
        item: AdapterItem.FavoriteItem
    ) {
        binding.ivFavoriteMovie.setOnClickListener {
            favoriteItemClickCallback?.invoke(item.favoriteMovieUI)
        }
    }
}
