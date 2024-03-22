package com.nazaroi.base.ktx

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDate.formatToString(pattern: String = "yyyy-MM-dd"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}

fun LocalDateTime.formatToString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}

fun String.toLocalDate(pattern: String = "yyyy-MM-dd"): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDate.parse(this, formatter)
}

fun String.toLocalDateTime(pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDateTime.parse(this, formatter)
}