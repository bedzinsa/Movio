package com.arunasbedzinskas.movio.domain.di

import com.arunasbedzinskas.movio.data.dataaccess.MoviesDataAccess
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.data.dataaccess.StaffPicksDataAccess
import com.arunasbedzinskas.movio.domain.usecase.GetIsAccountCreatedUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetIsAccountCreatedUseCaseImpl
import com.arunasbedzinskas.movio.domain.usecase.GetStaffPickMoviesUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetStaffPickMoviesUseCaseImpl
import com.arunasbedzinskas.movio.domain.usecase.SaveUserDataUseCase
import com.arunasbedzinskas.movio.domain.usecase.SaveUserDataUseCaseImpl
import com.arunasbedzinskas.movio.domain.usecase.ChangeFavoriteMovieUseCase
import com.arunasbedzinskas.movio.domain.usecase.ChangeFavoriteMovieUseCaseImpl
import com.arunasbedzinskas.movio.domain.usecase.GetFavoriteMoviesUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetFavoriteMoviesUseCaseImpl
import com.arunasbedzinskas.movio.domain.usecase.GetUserDataUseCase
import com.arunasbedzinskas.movio.domain.usecase.GetUserDataUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetIsAccountCreatedUseCase(
        localDataStore: LocalDataStore
    ): GetIsAccountCreatedUseCase = GetIsAccountCreatedUseCaseImpl(localDataStore)

    @Provides
    fun provideSaveUserDataUseCase(
        localDataStore: LocalDataStore
    ): SaveUserDataUseCase = SaveUserDataUseCaseImpl(localDataStore)

    @Provides
    fun provideGetUserDataUseCase(
        localDataStore: LocalDataStore
    ): GetUserDataUseCase = GetUserDataUseCaseImpl(localDataStore)

    @Provides
    fun provideGetStaffPickMoviesUseCase(
        staffPicksDataAccess: StaffPicksDataAccess,
        localDataStore: LocalDataStore
    ): GetStaffPickMoviesUseCase = GetStaffPickMoviesUseCaseImpl(staffPicksDataAccess, localDataStore)

    @Provides
    fun provideChangeFavoriteMovieUseCase(
        localDataStore: LocalDataStore
    ): ChangeFavoriteMovieUseCase = ChangeFavoriteMovieUseCaseImpl(localDataStore)

    @Provides
    fun provideGetFavoriteMoviesUseCase(
        moviesDataAccess: MoviesDataAccess,
        localDataStore: LocalDataStore
    ): GetFavoriteMoviesUseCase = GetFavoriteMoviesUseCaseImpl(moviesDataAccess, localDataStore)
}
