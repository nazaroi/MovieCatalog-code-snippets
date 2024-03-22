package com.nazaroi.data.remote.dto.review

data class ReviewDto(
    val author: String,
    val author_details: AuthorDto,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)