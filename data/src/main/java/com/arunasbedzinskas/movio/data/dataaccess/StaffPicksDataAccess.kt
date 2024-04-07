package com.arunasbedzinskas.movio.data.dataaccess

import android.content.Context
import android.util.Log
import com.arunasbedzinskas.movio.core.ext.fromAssetsToString
import com.arunasbedzinskas.movio.core.ext.fromJson
import com.arunasbedzinskas.movio.models.data.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface StaffPicksDataAccess {

    fun getAll(): Flow<List<Movie>>
}

internal class StaffPicksDataAccessImpl @Inject constructor(
    private val appContext: Context,
    private val coroutineScope: CoroutineScope,
    private val gson: Gson
) : StaffPicksDataAccess {

    private val moviesFlow = MutableStateFlow(listOf<Movie>())

    init {
        parseStaffPicks()
    }

    override fun getAll(): Flow<List<Movie>> = moviesFlow.asStateFlow()

    private fun parseStaffPicks() {
        coroutineScope.launch {
            val jsonString = appContext.fromAssetsToString(FILE_STAFF_PICKS, TAG)
            moviesFlow.value = gson.fromJson(jsonString)
        }
    }

    companion object {

        private const val TAG = "StaffPicksDataAccess"
        private const val FILE_STAFF_PICKS = "staff_picks.json"
    }
}
