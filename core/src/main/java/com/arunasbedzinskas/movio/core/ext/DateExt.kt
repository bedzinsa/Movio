package com.arunasbedzinskas.movio.core.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toYear(format: DateTimeFormatter = DateTimeFormatter.ISO_DATE): String =
    LocalDate.parse(this, format)
        .year
        .toString()