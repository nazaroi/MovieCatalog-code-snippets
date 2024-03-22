package com.nazaroi.domain.model.credit

import com.nazaroi.base.recyclerview.adapter.Identifiable
import com.nazaroi.common.constant.ImageBaseUrl
import com.nazaroi.domain.enums.Gender

sealed class CreditMember(
    open val adult: Boolean,
    open val memberId: String,
    open val gender: Gender,
    open val id: Int,
    open val knownForDepartment: String,
    open val name: String,
    open val originalName: String,
    open val popularity: Double,
    open val profilePath: String?,
) : Identifiable {
    override val uniqueId: String
        get() = memberId
}

fun CreditMember.fullProfilePath(size: String = "w185"): String? =
    profilePath?.let { "$ImageBaseUrl$size$it" }