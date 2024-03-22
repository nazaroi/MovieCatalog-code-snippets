package com.nazaroi.data.remote.dto.review

data class MovieReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<ReviewDto>,
    val total_pages: Int,
    val total_results: Int
)