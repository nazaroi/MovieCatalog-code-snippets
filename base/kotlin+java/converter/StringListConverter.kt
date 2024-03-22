package com.nazaroi.base.converter

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun stringListToString(stringList: List<String>?): String {
        return stringList?.joinToString(separator = ",") ?: ""
    }

    @TypeConverter
    fun stringToStringList(string: String?): List<String> {
        if (string.isNullOrEmpty()) return emptyList()
        return string.split(",").map { it.trim() }
    }
}