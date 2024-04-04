package com.arunasbedzinskas.movio.ui.di

import android.content.Context
import com.arunasbedzinskas.movio.ui.screens.login.SignUpUiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object UIProvidersModule {

    @Provides
    fun provideSignUpUiProvider(
        @ApplicationContext context: Context,
    ): SignUpUiProvider = SignUpUiProvider(context)
}
