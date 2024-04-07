package com.arunasbedzinskas.movio.ui.screens.home

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.toSpannable
import com.arunasbedzinskas.movio.models.Const.EMPTY_SPACE
import com.arunasbedzinskas.movio.ui.R
import javax.inject.Inject

class HomeProvider @Inject constructor(private val appContext: Context) {

    fun getFavoritesLabel() = SpannableStringBuilder()
        .append(appContext.getString(R.string.generic_your))
        .append(EMPTY_SPACE)
        .bold { append(appContext.getString(R.string.home_label_favorites)) }
        .toSpannable() as SpannableString

    fun getStaffPicksLabel() = SpannableStringBuilder()
        .append(appContext.getString(R.string.generic_our))
        .append(EMPTY_SPACE)
        .bold { append(appContext.getString(R.string.home_label_staff_picks)) }
        .toSpannable() as SpannableString
}
