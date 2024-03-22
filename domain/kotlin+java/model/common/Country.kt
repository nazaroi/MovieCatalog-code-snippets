package com.nazaroi.domain.model.common

import com.nazaroi.base.recyclerview.adapter.Identifiable

data class Country(
    val code: String, val name: String
) : Identifiable {
    override val uniqueId: String
        get() = code
}