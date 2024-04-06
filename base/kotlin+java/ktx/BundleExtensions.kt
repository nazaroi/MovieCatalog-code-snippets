package com.nazaroi.base.ktx

import android.os.Bundle

inline fun <reified T : Enum<T>> Bundle.putEnum(key: String, value: T) {
    putString(key, value.name)
}

inline fun <reified T : Enum<T>> Bundle.getEnum(key: String, defaultValue: T? = null): T? {
    val name = getString(key) ?: return defaultValue
    return enumValueOf<T>(name)
}

inline fun <reified T : Enum<T>> Bundle.getEnumOrThrow(key: String): T {
    val enumName =
        getString(key) ?: throw IllegalArgumentException("Enum for key $key not found in Bundle.")

    return enumValueOf<T>(enumName)
}