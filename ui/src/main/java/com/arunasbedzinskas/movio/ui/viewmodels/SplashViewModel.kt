package com.arunasbedzinskas.movio.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.domain.usecase.GetIsAccountCreatedUseCase
import com.arunasbedzinskas.movio.models.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getIsAccountCreatedUseCase: GetIsAccountCreatedUseCase
) : ViewModel() {

    private val _accountCreatedState = MutableStateFlow<UiState<Boolean>>(UiState.LoadingState())
    val accountCreatedState = _accountCreatedState.asStateFlow()

    init {
        getIsAccountCreatedFlow()
    }

    private fun getIsAccountCreatedFlow() {
        viewModelScope.launch {
            _accountCreatedState.value = UiState.NormalState(getIsAccountCreatedUseCase())
        }
    }
}
