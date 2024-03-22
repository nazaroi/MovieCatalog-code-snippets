package com.nazaroi.base.ktx

import android.content.Context
import android.util.TypedValue
import kotlin.math.pow
import kotlin.math.roundToInt

fun Float.roundDecimal(digits: Int = 1): Float {
    require(digits >= 0) { "Number of digits must be non-negative." }

    val multiplier = 10.0.pow(digits).toFloat()
    return (this * multiplier).roundToInt() / multiplier
}

fun Double.roundDecimal(digits: Int = 1): Double {
    require(digits >= 0) { "Number of digits must be non-negative." }

    val multiplier = 10.0.pow(digits)
    return (this * multiplier).roundToInt() / multiplier
}

fun Float.dpToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, context.displayMetrics
    ).toInt()
}