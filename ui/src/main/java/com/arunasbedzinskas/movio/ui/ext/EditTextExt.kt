package com.arunasbedzinskas.movio.ui.ext

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.isInputTypePassword(): Boolean {
    val variation = inputType and (EditorInfo.TYPE_MASK_CLASS or EditorInfo.TYPE_MASK_VARIATION)
    return variation == (EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_PASSWORD)
            || variation == (EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD)
            || variation == (EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD)
}

fun EditText.isVisiblePasswordInputType(): Boolean {
    val variation = inputType and (EditorInfo.TYPE_MASK_CLASS or EditorInfo.TYPE_MASK_VARIATION)
    return variation == (EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
}

fun EditText.isAnyPassword(): Boolean = isInputTypePassword() || isVisiblePasswordInputType()
