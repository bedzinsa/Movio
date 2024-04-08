package com.arunasbedzinskas.movio.core.ext

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Currency

fun Long.formatToCurrencyAmount(): String = with(DecimalFormat()) {
    isGroupingUsed = true
    format(this@formatToCurrencyAmount)
}

fun Double.round(
    decimals: Int = 2,
    roundingMode: RoundingMode = RoundingMode.HALF_UP
): String = with(BigDecimal(this)) {
    setScale(decimals, roundingMode).toString()
}
