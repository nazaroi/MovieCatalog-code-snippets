package com.nazaroi.domain.model.credit

import com.nazaroi.domain.enums.Gender

data class CrewMember(
    override val adult: Boolean,
    override val memberId: String,
    override val gender: Gender,
    override val id: Int,
    override val knownForDepartment: String,
    override val name: String,
    override val originalName: String,
    override val popularity: Double,
    override val profilePath: String?,
    val department: String,
    val job: String
) : CreditMember(
    adult, memberId, gender, id, knownForDepartment, name, originalName, popularity, profilePath
)