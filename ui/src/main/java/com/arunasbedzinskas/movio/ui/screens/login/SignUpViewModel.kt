package com.arunasbedzinskas.movio.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunasbedzinskas.movio.domain.usecase.SaveUserDataUseCase
import com.arunasbedzinskas.movio.domain.validator.SignUpValidator
import com.arunasbedzinskas.movio.models.state.PasswordValidationState
import com.arunasbedzinskas.movio.models.state.UiState
import com.arunasbedzinskas.movio.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val signUpUiProvider: SignUpUiProvider
) : ViewModel() {

    private val _signUpResult = MutableLiveData<UiState<Unit>>()
    private val _nameError = MutableLiveData<String?>(null)
    private val _emailError = MutableLiveData<String?>(null)
    private val _passwordError = MutableLiveData<String?>(null)

    val signUpResult: LiveData<UiState<Unit>>
        get() = _signUpResult
    val nameError: LiveData<String?>
        get() = _nameError
    val emailError: LiveData<String?>
        get() = _emailError
    val passwordError: LiveData<String?>
        get() = _passwordError


    var name: String = ""
        set(value) {
            field = value
            _nameError.value = null
        }
    var email: String = ""
        set(value) {
            field = value
            _emailError.value = null
        }
    var password: String = ""
        set(value) {
            field = value
            _passwordError.value = null
        }
    var cPassword: String = ""
        set(value) {
            field = value
            _passwordError.value = null
        }

    fun signUp() {
        viewModelScope.launch {
            if (!isDataValid()) return@launch

            // Password left out intentionally - as it should not be saved locally
            // Using stub image due to extended development time to implement camera image
            val isSuccessful = saveUserDataUseCase(name, email, R.drawable.stub_logo)
            _signUpResult.value = if (isSuccessful)
                UiState.NormalState(Unit)
            else
                UiState.ErrorState("Something went wrong during sign up process.")
        }
    }

    private fun isDataValid(): Boolean {
        val passValidationState = SignUpValidator.getPasswordsValidationState(password, cPassword)
        return when {
            !SignUpValidator.isNameValid(name) -> {
                _nameError.value = signUpUiProvider.getNameErrorString()
                false
            }

            !SignUpValidator.isEmailValid(email) -> {
                _emailError.value = signUpUiProvider.getEmailErrorString()

                false
            }

            passValidationState != PasswordValidationState.Valid -> {
                _passwordError.value = signUpUiProvider.getPasswordErrorString(passValidationState)
                false
            }

            else -> true
        }
    }
}
