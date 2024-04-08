package com.arunasbedzinskas.movio.data.dataaccess

import android.content.Context
import com.arunasbedzinskas.movio.core.ext.fromAssetsToString
import com.arunasbedzinskas.movio.core.ext.fromJson
import com.arunasbedzinskas.movio.models.data.Movie
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface MoviesDataAccess {

    fun getAll(): Flow<List<Movie>>
}

internal class MoviesDataAccessImpl @Inject constructor(
    private val appContext: Context,
    private val coroutineScope: CoroutineScope,
    private val gson: Gson
): MoviesDataAccess {

    private val moviesFlow = MutableStateFlow(listOf<Movie>())

    init {
        parseMovies()
    }

    override fun getAll(): Flow<List<Movie>> = moviesFlow.asStateFlow()

    private fun parseMovies() {
        coroutineScope.launch {
            val jsonString = appContext.fromAssetsToString(
                FILE_MOVIES,
                TAG
            )
            moviesFlow.value = gson.fromJson(jsonString)
        }
    }

    companion object {

        private const val TAG = "MoviesDataAccess"
        private const val FILE_MOVIES = "movies.json"
    }
}
