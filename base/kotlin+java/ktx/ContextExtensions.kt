package com.nazaroi.base.ktx

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

val Context.displayMetrics: DisplayMetrics
    get() = resources.displayMetrics