package com.arunasbedzinskas.movio.ui.components.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arunasbedzinskas.movio.ui.R

@Composable
fun Rating(
    rating: Int,
    modifier: Modifier = Modifier,
    maxRating: Int = 5
) {

    Row(modifier = modifier.wrapContentWidth()) {
        for (i in 1 .. maxRating) {
            val painter = if (i <= rating) {
                painterResource(R.drawable.ic_star_filled)
            } else {
                painterResource(R.drawable.ic_star_unfilled)
            }

            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Unspecified
            )
        }
    }
}
