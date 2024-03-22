package com.nazaroi.domain.model.common

import com.nazaroi.base.recyclerview.adapter.Identifiable

data class Company(
    val id: Int, val logoPath: String?, val name: String, val originCountry: String
) : Identifiable {
    override val uniqueId: Int
        get() = id
}