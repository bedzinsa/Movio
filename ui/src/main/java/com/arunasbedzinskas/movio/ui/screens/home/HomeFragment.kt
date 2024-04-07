package com.arunasbedzinskas.movio.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arunasbedzinskas.movio.core.ext.toPx
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import com.arunasbedzinskas.movio.ui.R
import com.arunasbedzinskas.movio.ui.base.BaseFragment
import com.arunasbedzinskas.movio.ui.callbacks.MovieClickCallback
import com.arunasbedzinskas.movio.ui.databinding.FragmentHomeBinding
import com.arunasbedzinskas.movio.ui.decoration.LinearLayoutMarginItemDecoration
import com.arunasbedzinskas.movio.ui.ext.navigate
import com.arunasbedzinskas.movio.ui.viewmodels.FavoriteViewModel
import com.arunasbedzinskas.movio.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initObservers()
    }

    private fun initUI() {
        val movieClickCallback = object : MovieClickCallback {

            override fun onItemClick(item: CondensedMovieUI) = navigateToMovieDetails(item.id)

            override fun onFavoriteClick(item: CondensedMovieUI) = favoriteViewModel.onFavoriteChanged(item)
        }
        binding?.rvHome?.apply {
            addItemDecoration(LinearLayoutMarginItemDecoration(context?.toPx(24) ?: 0))
            adapter = HomeAdapter(
                movieClickCallback = movieClickCallback,
                searchClickCallback = { navigateToSearch() },
                favoriteItemClickCallback = { navigateToMovieDetails(it.id)}
            )
        }
    }

    private fun initObservers() {
        homeViewModel.homeListUI.observe(viewLifecycleOwner) { items ->
            val adapter = binding?.rvHome?.adapter as? HomeAdapter
            adapter?.submitItems(items)
        }
    }

    private fun navigateToMovieDetails(id: Long) = Unit // TODO

    private fun navigateToSearch() = navigate(R.id.hf_to_searchMoviesFragment)
}
