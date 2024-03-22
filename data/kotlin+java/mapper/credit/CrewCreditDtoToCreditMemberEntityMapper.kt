package com.nazaroi.data.mapper.credit

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.credit.CreditMemberEntity
import com.nazaroi.data.remote.dto.credit.CrewCreditDto
import com.nazaroi.domain.enums.CreditMemberType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrewCreditDtoToCreditMemberEntityMapper @Inject constructor() :
    DtoToEntityMapper<CrewCreditDto, CreditMemberEntity> {
    override fun map(dto: CrewCreditDto): CreditMemberEntity {
        return with(dto) {
            CreditMemberEntity(
                id = id,
                adult = adult,
                member_id = credit_id,
                gender = gender,
                known_for_department = known_for_department,
                name = name,
                original_name = original_name,
                popularity = popularity,
                profile_path = profile_path,
                type = CreditMemberType.Crew,
                department = department,
                job = job
            )
        }
    }
}