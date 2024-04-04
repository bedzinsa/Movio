package com.arunasbedzinskas.movio.domain.validator

import android.util.Patterns
import com.arunasbedzinskas.movio.models.state.PasswordValidationState

object SignUpValidator {

    fun isNameValid(name: String): Boolean = name.isNotBlank()

    fun isEmailValid(email: String): Boolean = email.isNotBlank()
            && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun getPasswordsValidationState(password: String, cPassword: String): PasswordValidationState =
        when {
            password.length < PASS_MIN -> PasswordValidationState.MustBe6Characters
            password != cPassword -> PasswordValidationState.NotMatches
            else -> PasswordValidationState.Valid
        }

    private const val PASS_MIN = 6
}
