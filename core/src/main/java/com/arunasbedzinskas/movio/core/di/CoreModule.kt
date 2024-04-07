package com.arunasbedzinskas.movio.core.di

import com.arunasbedzinskas.movio.core.dispatcher.AppDispatchers
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAppDispatchers(): AppDispatchers = AppDispatchers(
        Dispatchers.Main,
        Dispatchers.IO,
        Dispatchers.Default
    )

    @Provides
    fun provideCoroutineScope(
        appDispatchers: AppDispatchers
    ) = CoroutineScope(appDispatchers.io + SupervisorJob())
}
