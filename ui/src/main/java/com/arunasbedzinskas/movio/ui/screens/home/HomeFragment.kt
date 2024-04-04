package com.arunasbedzinskas.movio.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arunasbedzinskas.movio.ui.base.BaseFragment
import com.arunasbedzinskas.movio.ui.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

}
