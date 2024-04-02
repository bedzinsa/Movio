package com.arunasbedzinskas.movio.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.domain.GetIsAccountCreatedUseCase
import com.arunasbedzinskas.movio.domain.dispatcher.AppDispatchers
import com.arunasbedzinskas.movio.models.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.update
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
