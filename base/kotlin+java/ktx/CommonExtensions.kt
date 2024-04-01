package com.nazaroi.base.ktx

import android.util.Log
import com.nazaroi.base.BuildConfig

inline fun <reified T> T.logD(message: String, vararg args: Any) {
    if (BuildConfig.DEBUG) {
        Log.d(T::class.simpleName, String.format(message, *args))
    }
}

inline fun <reified T> T.logI(message: String, vararg args: Any) {
    if (BuildConfig.DEBUG) {
        Log.i(T::class.simpleName, String.format(message, *args))
    }
}

inline fun <reified T> T.logW(message: String, vararg args: Any) {
    if (BuildConfig.DEBUG) {
        Log.w(T::class.simpleName, String.format(message, *args))
    }
}

inline fun <reified T> T.logE(message: String, vararg args: Any) {
    if (BuildConfig.DEBUG) {
        Log.e(T::class.simpleName, String.format(message, *args))
    }
}