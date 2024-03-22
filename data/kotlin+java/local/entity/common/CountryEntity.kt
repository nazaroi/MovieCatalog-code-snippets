package com.nazaroi.data.local.entity.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val code: String, val name: String
)