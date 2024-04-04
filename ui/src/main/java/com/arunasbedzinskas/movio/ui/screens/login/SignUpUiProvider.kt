package com.arunasbedzinskas.movio.ui.screens.login

import android.content.Context
import com.arunasbedzinskas.movio.models.state.PasswordValidationState
import com.arunasbedzinskas.movio.ui.R
import javax.inject.Inject

class SignUpUiProvider @Inject constructor(
    private val appContext: Context
) {

    fun getNameErrorString(): String = appContext.getString(R.string.input_name_error_blank)

    fun getEmailErrorString(): String = appContext.getString(R.string.input_email_error_invalid)

    fun getPasswordErrorString(passwordValidationState: PasswordValidationState): String? =
        when (passwordValidationState) {
            PasswordValidationState.NotMatches -> appContext.getString(R.string.input_password_error_does_not_match)
            PasswordValidationState.MustBe6Characters -> appContext.getString(R.string.input_password_error_length)
            PasswordValidationState.Valid -> null
        }
}
