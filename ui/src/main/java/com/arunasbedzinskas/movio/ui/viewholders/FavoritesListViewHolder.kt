package com.arunasbedzinskas.movio.ui.viewholders

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.arunasbedzinskas.movio.core.ext.toPx
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder
import com.arunasbedzinskas.movio.ui.databinding.HolderFavoriteListBinding
import com.arunasbedzinskas.movio.ui.decoration.LinearLayoutMarginItemDecoration
import com.arunasbedzinskas.movio.ui.screens.home.FavoritesAdapter

class FavoritesListViewHolder(val binding: HolderFavoriteListBinding) : BaseViewHolder(binding) {

    init {
        initUI()
    }

    override fun onBind(item: AdapterItem) {
        if (item !is AdapterItem.FavoritesListItem) return
        val adapter = binding.rvFavorites.adapter as? FavoritesAdapter
        adapter?.let {
             adapter.submitItems(item.favoritesUI.favoriteUIs)
        } ?: Log.e(TAG, "Unexpected adapter set to RecyclerView")
    }

    private fun initUI() {
        with(binding.rvFavorites) {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(LinearLayoutMarginItemDecoration(space = context.toPx(24)))
        }
    }

    companion object {

        private const val TAG = "FavoritesListViewHolder"
    }
}
