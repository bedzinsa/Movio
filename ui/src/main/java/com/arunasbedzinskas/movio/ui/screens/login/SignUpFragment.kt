package com.arunasbedzinskas.movio.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.arunasbedzinskas.movio.models.state.UiState
import com.arunasbedzinskas.movio.ui.R
import com.arunasbedzinskas.movio.ui.base.BaseFragment
import com.arunasbedzinskas.movio.ui.databinding.FragmentSignUpBinding
import com.arunasbedzinskas.movio.ui.ext.navigate
import com.arunasbedzinskas.movio.ui.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initUI()
    }

    private fun initObservers() {
        with(signUpViewModel) {
            nameError.observe(viewLifecycleOwner) { error ->
                binding?.ifName?.setError(error)
            }
            emailError.observe(viewLifecycleOwner) { error ->
                binding?.ifEmail?.setError(error)
            }
            passwordError.observe(viewLifecycleOwner) { error ->
                binding?.ifPassword?.setError(error)
            }
            signUpResult.observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.NormalState -> navigateToHome()
                    is UiState.ErrorState -> showErrorToast(uiState.error)
                    else -> Unit
                }
            }
        }
    }

    private fun initUI() {
        binding?.apply {
            ifName.doOnTextChanged { text, _, _, _ ->
                signUpViewModel.name = text.toString()
            }
            ifEmail.doOnTextChanged { text, _, _, _ ->
                signUpViewModel.email = text.toString()
            }
            ifPassword.doOnTextChanged { text, _, _, _ ->
                signUpViewModel.password = text.toString()
            }
            ifConfPassword.doOnTextChanged { text, _, _, _ ->
                signUpViewModel.cPassword = text.toString()
            }
            mbSignUp.setOnClickListener { signUpViewModel.signUp() }
        }
    }

    private fun showErrorToast(message: String) = context?.let {
        Toast.makeText(it, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToHome() = navigate(R.id.suf_to_homeFragment)
}
