package com.arunasbedzinskas.movio.ui.splash

import androidx.lifecycle.ViewModel
import com.arunasbedzinskas.movio.domain.GetIsAccountCreatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getIsAccountCreatedUseCase: GetIsAccountCreatedUseCase
) : ViewModel() {

    init {
        getIsAccountCreatedUseCase
    }

}
