package com.arunasbedzinskas.movio.ui.ext

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.arunasbedzinskas.movio.ui.R

fun slideNavOptions() = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()