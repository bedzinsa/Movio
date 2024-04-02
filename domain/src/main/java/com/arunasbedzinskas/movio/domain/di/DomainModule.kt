package com.arunasbedzinskas.movio.domain.di

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.domain.GetIsAccountCreatedUseCase
import com.arunasbedzinskas.movio.domain.GetIsAccountCreatedUseCaseImpl
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
}
