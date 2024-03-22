package com.nazaroi.data.local.entity.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey val code: String, val english_name: String, val name: String
)