package com.nazaroi.data.local.mapper.credit

import com.nazaroi.base.mapper.EntityToDomainMapper
import com.nazaroi.data.local.entity.credit.CreditMemberEntity
import com.nazaroi.domain.enums.CreditMemberType
import com.nazaroi.domain.enums.Gender
import com.nazaroi.domain.model.credit.CrewMember
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreditMemberEntityToCrewMemberMapper @Inject constructor() :
    EntityToDomainMapper<CreditMemberEntity, CrewMember> {
    override fun map(entity: CreditMemberEntity): CrewMember {
        if (entity.type != CreditMemberType.Crew || entity.department == null || entity.job == null) {
            throw IllegalArgumentException("Entity is not a CrewMember or lacks CrewMember specific fields.")
        }

        return CrewMember(
            adult = entity.adult,
            memberId = entity.member_id,
            gender = Gender.fromId(entity.gender),
            id = entity.id,
            knownForDepartment = entity.known_for_department,
            name = entity.name,
            originalName = entity.original_name,
            popularity = entity.popularity,
            profilePath = entity.profile_path,
            department = entity.department,
            job = entity.job
        )
    }
}