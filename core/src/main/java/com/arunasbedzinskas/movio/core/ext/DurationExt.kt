package com.arunasbedzinskas.movio.core.ext

import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

fun Duration.toHoursMinutes() = buildString {
    val hours = inWholeHours
    val minutes = minus(hours.hours).inWholeMinutes
    if (hours > 0) {
        append(hours)
        append("h ")
    }
    if (minutes > 0) {
        append(minutes)
        append("m")
    }
}.trim()