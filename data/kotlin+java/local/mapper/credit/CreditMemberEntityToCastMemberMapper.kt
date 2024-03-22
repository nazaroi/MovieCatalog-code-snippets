package com.nazaroi.data.local.mapper.credit

import com.nazaroi.base.mapper.EntityToDomainMapper
import com.nazaroi.data.local.entity.credit.CreditMemberEntity
import com.nazaroi.domain.enums.CreditMemberType
import com.nazaroi.domain.enums.Gender
import com.nazaroi.domain.model.credit.CastMember
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreditMemberEntityToCastMemberMapper @Inject constructor() :
    EntityToDomainMapper<CreditMemberEntity, CastMember> {
    override fun map(entity: CreditMemberEntity): CastMember {
        if (entity.type != CreditMemberType.Cast || entity.cast_id == null || entity.character == null || entity.order == null) {
            throw IllegalArgumentException("Entity is not a CastMember or lacks CastMember specific fields.")
        }

        return CastMember(
            adult = entity.adult,
            memberId = entity.member_id,
            gender = Gender.fromId(entity.gender),
            id = entity.id,
            knownForDepartment = entity.known_for_department,
            name = entity.name,
            originalName = entity.original_name,
            popularity = entity.popularity,
            profilePath = entity.profile_path,
            castId = entity.cast_id,
            character = entity.character,
            order = entity.order
        )
    }
}