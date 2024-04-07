package com.arunasbedzinskas.movio.ui.viewholders

import coil.load
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder
import com.arunasbedzinskas.movio.ui.databinding.HolderFavoriteBinding

class FavoriteViewHolder(val binding: HolderFavoriteBinding) : BaseViewHolder(binding) {

    override fun onBind(item: AdapterItem) {
        if (item !is AdapterItem.FavoriteItem) return
        binding.ivFavoriteMovie.load(item.favoriteMovieUI.logo)
    }
}
