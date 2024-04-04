package com.arunasbedzinskas.movio.ui.components

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.arunasbedzinskas.movio.ui.R
import com.arunasbedzinskas.movio.ui.databinding.ViewInputFieldBinding
import com.arunasbedzinskas.movio.ui.ext.isAnyPassword
import com.arunasbedzinskas.movio.ui.ext.isInputTypePassword

class InputField(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val binding = ViewInputFieldBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(context.obtainStyledAttributes(attrs, R.styleable.InputField)) {
            binding.inputField.inputType =
                getInt(R.styleable.InputField_android_inputType, EditorInfo.TYPE_NULL)
            binding.inputField.imeOptions =
                getInt(R.styleable.InputField_android_imeOptions, EditorInfo.IME_ACTION_NONE)
            binding.inputField.hint = getString(R.styleable.InputField_android_hint)
            recycle()
        }

        setupInputTypeIcon()
        setupInputListener()
    }

    fun getText() = binding.inputField.text.toString()

    fun setError(errorText: String?) {
        with(binding.tvInputFieldError) {
            text = errorText
            isGone = errorText.isNullOrBlank()
        }
    }

    fun clearError() = setError(null)

    fun doOnTextChanged(
        action: (
            text: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) -> Unit
    ): TextWatcher = binding.inputField.doOnTextChanged(action)

    private fun setupInputTypeIcon() {
        val inputTypeIconVisible = binding.inputField.isAnyPassword()
        val iconRes = if (binding.inputField.isInputTypePassword())
            R.drawable.ic_eye_off_outline
        else
            R.drawable.ic_eye_outline

        binding.inputFieldIcon.apply {
            setImageResource(iconRes)
            isVisible = inputTypeIconVisible
            setOnClickListener { onInputTypeIconClicked() }
        }
    }

    private fun setupInputListener() {
        binding.inputField.doOnTextChanged { _, _, _, _ ->
            clearError()
        }
    }

    private fun onInputTypeIconClicked() {
        val newInputType = if (binding.inputField.isInputTypePassword())
            EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        else
            EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_PASSWORD

        binding.inputField.inputType = newInputType
        setupInputTypeIcon()
    }
}
