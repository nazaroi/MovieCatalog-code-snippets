package com.nazaroi.data.local.entity.review

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey val id: String,
    val author_username: String,
    val content: String,
    val created_at: String,
    val updated_at: String,
    val url: String
)