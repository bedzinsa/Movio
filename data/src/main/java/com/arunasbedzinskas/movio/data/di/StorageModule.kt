package com.arunasbedzinskas.movio.data.di

import android.content.Context
import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    fun provideLocalDataStore(
        @ApplicationContext context: Context
    ): LocalDataStore = LocalDataStore(context)
}
