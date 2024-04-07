package com.arunasbedzinskas.movio.data.di

import android.content.Context
import com.arunasbedzinskas.movio.data.dataaccess.MoviesDataAccess
import com.arunasbedzinskas.movio.data.dataaccess.MoviesDataAccessImpl
import com.arunasbedzinskas.movio.data.dataaccess.StaffPicksDataAccess
import com.arunasbedzinskas.movio.data.dataaccess.StaffPicksDataAccessImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DataAccessModule {

    @Provides
    fun provideStaffPicksDataAccess(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope,
        gson: Gson
    ): StaffPicksDataAccess = StaffPicksDataAccessImpl(context, coroutineScope, gson)

    @Provides
    fun provideMoviesDataAccess(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope,
        gson: Gson
    ): MoviesDataAccess = MoviesDataAccessImpl(context, coroutineScope, gson)
}
