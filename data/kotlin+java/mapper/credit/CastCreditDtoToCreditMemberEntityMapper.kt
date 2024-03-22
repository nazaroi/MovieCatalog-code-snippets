package com.nazaroi.data.mapper.credit

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.credit.CreditMemberEntity
import com.nazaroi.data.remote.dto.credit.CastCreditDto
import com.nazaroi.domain.enums.CreditMemberType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastCreditDtoToCreditMemberEntityMapper @Inject constructor() :
    DtoToEntityMapper<CastCreditDto, CreditMemberEntity> {
    override fun map(dto: CastCreditDto): CreditMemberEntity {
        return with(dto) {
            CreditMemberEntity(
                adult = adult,
                member_id = credit_id,
                gender = gender,
                id = id,
                known_for_department = known_for_department,
                name = name,
                original_name = original_name,
                popularity = popularity,
                profile_path = profile_path,
                type = CreditMemberType.Cast,
                cast_id = cast_id,
                character = character,
                order = order
            )
        }
    }
}