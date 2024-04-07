package com.arunasbedzinskas.movio.ui.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arunasbedzinskas.movio.models.state.UiState
import com.arunasbedzinskas.movio.ui.R
import com.arunasbedzinskas.movio.ui.base.BaseFragment
import com.arunasbedzinskas.movio.ui.databinding.FragmentSplashBinding
import com.arunasbedzinskas.movio.ui.ext.navigate
import com.arunasbedzinskas.movio.ui.ext.repeatOnStarted
import com.arunasbedzinskas.movio.ui.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        repeatOnStarted {
            splashViewModel.accountCreatedState.collect { uiState ->
                if (uiState !is UiState.NormalState) return@collect
                val isCreated = uiState.data

                if (isCreated) {
                    navigateToHome()
                } else {
                    navigateToSignUp()
                }
            }
        }
    }

    private fun navigateToHome() = navigate(R.id.sf_to_homeFragment)

    private fun navigateToSignUp() = navigate(R.id.sf_to_signUpFragment)
}
