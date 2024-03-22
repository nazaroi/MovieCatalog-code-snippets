package com.nazaroi.base.converter

import androidx.room.TypeConverter

class IntListConverter {
    @TypeConverter
    fun intListToString(intList: List<Int>?): String {
        return intList?.joinToString(separator = ",") ?: ""
    }

    @TypeConverter
    fun stringToIntList(string: String?): List<Int> {
        if (string.isNullOrEmpty()) return emptyList()
        return string.split(",").mapNotNull { it.toIntOrNull() }
    }
}