package com.nazaroi.data.local.entity.credit

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nazaroi.domain.enums.CreditMemberType

@Entity(tableName = "credit_members")
data class CreditMemberEntity(
    val adult: Boolean,
    @PrimaryKey val member_id: String,
    val id: Int,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val type: CreditMemberType,
    // Specific to cast member
    val cast_id: Int? = null,
    val character: String? = null,
    val order: Int? = null,
    // Specific to crew member
    val department: String? = null,
    val job: String? = null
)