package com.nazaroi.data.local.entity.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey val id: Int, val logo_path: String?, val name: String, val origin_country: String
)