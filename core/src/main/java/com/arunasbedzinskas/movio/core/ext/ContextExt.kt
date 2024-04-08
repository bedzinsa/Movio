package com.arunasbedzinskas.movio.core.ext

import android.content.Context
import android.util.Log
import android.util.TypedValue

private const val TAG = "ContextExt"

fun Context.fromAssetsToString(
    filePath: String,
    logTag: String = TAG
) = try {
    buildString {
        assets
            .open(filePath)
            .bufferedReader()
            .useLines { lines -> lines.forEach { append(it) } }
    }
} catch (e: Exception) {
    Log.e(logTag, "Error while parsing data from assets", e)
    ""
}

fun Context.toPx(dp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
).toInt()
