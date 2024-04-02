package com.arunasbedzinskas.movio.domain.di

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.domain.GetIsAccountCreatedUseCase
import com.arunasbedzinskas.movio.domain.GetIsAccountCreatedUseCaseImpl
import com.arunasbedzinskas.movio.domain.dispatcher.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideAppDispatchers(): AppDispatchers = AppDispatchers(
        Dispatchers.Main,
        Dispatchers.IO,
        Dispatchers.Default
    )

    @Provides
    fun provideGetIsAccountCreatedUseCase(
        localDataStore: LocalDataStore
    ): GetIsAccountCreatedUseCase = GetIsAccountCreatedUseCaseImpl(localDataStore)
}
