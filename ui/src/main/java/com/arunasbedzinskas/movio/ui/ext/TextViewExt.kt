package com.arunasbedzinskas.movio.ui.ext

import android.widget.TextView
import androidx.annotation.DrawableRes

fun TextView.setDrawableLeft(@DrawableRes drawableRes: Int) =
    setCompoundDrawablesRelativeWithIntrinsicBounds(drawableRes, 0, 0, 0)
