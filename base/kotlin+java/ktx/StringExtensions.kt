package com.nazaroi.base.ktx

inline fun <reified T : Enum<T>> String.toEnumOrNull(): T? {
    return try {
        enumValueOf<T>(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}