package com.nazaroi.domain.model.credit

import com.nazaroi.domain.enums.Gender

data class CastMember(
    override val adult: Boolean,
    override val memberId: String,
    override val gender: Gender,
    override val id: Int,
    override val knownForDepartment: String,
    override val name: String,
    override val originalName: String,
    override val popularity: Double,
    override val profilePath: String?,
    val castId: Int,
    val character: String,
    val order: Int
) : CreditMember(
    adult, memberId, gender, id, knownForDepartment, name, originalName, popularity, profilePath
)