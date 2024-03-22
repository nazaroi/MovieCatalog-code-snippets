package com.nazaroi.domain.model.review

import com.nazaroi.base.recyclerview.adapter.Identifiable

data class Review(
    val author: Author,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
) : Identifiable {
    override val uniqueId: String
        get() = id
}