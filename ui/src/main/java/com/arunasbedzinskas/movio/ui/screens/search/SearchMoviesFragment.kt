package com.arunasbedzinskas.movio.ui.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arunasbedzinskas.movio.core.ext.toPx
import com.arunasbedzinskas.movio.models.ui.CondensedMovieUI
import com.arunasbedzinskas.movio.ui.base.BaseFragment
import com.arunasbedzinskas.movio.ui.callbacks.MovieClickCallback
import com.arunasbedzinskas.movio.ui.databinding.FragmentSearchMoviesBinding
import com.arunasbedzinskas.movio.ui.decoration.LinearLayoutMarginItemDecoration
import com.arunasbedzinskas.movio.ui.ext.navigate
import com.arunasbedzinskas.movio.ui.ext.navigateUp
import com.arunasbedzinskas.movio.ui.ext.repeatOnStarted
import com.arunasbedzinskas.movio.ui.viewmodels.FavoriteViewModel
import com.arunasbedzinskas.movio.ui.viewmodels.SearchMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMoviesFragment : BaseFragment<FragmentSearchMoviesBinding>() {

    private val searchMoviesViewModel: SearchMoviesViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchMoviesBinding = FragmentSearchMoviesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initCollectors()
    }

    private fun initUI() {
        val movieClickCallback = object : MovieClickCallback {
            override fun onItemClick(item: CondensedMovieUI) =
                navigateToMovieDetails(item.id)

            override fun onFavoriteClick(item: CondensedMovieUI) =
                favoriteViewModel.onFavoriteChanged(item.id, item.isFavorite)
        }

        binding?.apply {
            tSearchMovies.setNavigationOnClickListener { navigateUp() }
            ifSearchBar.doOnTextChanged { text, _, _, _ -> searchMoviesViewModel.searchBy(text) }

            rvSearchMovies.addItemDecoration(
                LinearLayoutMarginItemDecoration(context?.toPx(24) ?: 0)
            )
            rvSearchMovies.adapter = SearchMoviesAdapter(movieClickCallback)
        }
    }

    private fun initCollectors() {
        repeatOnStarted {
            searchMoviesViewModel.movies.collect { movies ->
                val adapter = binding?.rvSearchMovies?.adapter as? SearchMoviesAdapter
                adapter?.submitItems(movies)
            }
        }
    }

    private fun navigateToMovieDetails(id: Long) =
        navigate(SearchMoviesFragmentDirections.smfToMovieDetailsFragment(id))
}
