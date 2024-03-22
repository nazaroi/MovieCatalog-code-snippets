package com.nazaroi.data.local.entity.review

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class AuthorEntity(
    val avatar_path: String?,
    @PrimaryKey val username: String,
    val rating: Double?,
    val name: String
)