package com.nazaroi.domain.model.review

import com.nazaroi.base.recyclerview.adapter.Identifiable

data class Author(
    val name: String, val username: String, val avatarPath: String?, val rating: Double?
) : Identifiable {
    override val uniqueId: String
        get() = username
}